package com.runanywhere.startup_hackathon20

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.runanywhere.sdk.public.RunAnywhere
import com.runanywhere.sdk.public.extensions.listAvailableModels
import com.runanywhere.sdk.models.ModelInfo
import com.runanywhere.sdk.models.RunAnywhereGenerationOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Simple Message Data Class
data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

// ViewModel
class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _availableModels = MutableStateFlow<List<ModelInfo>>(emptyList())
    val availableModels: StateFlow<List<ModelInfo>> = _availableModels

    private val _downloadProgress = MutableStateFlow<Float?>(null)
    val downloadProgress: StateFlow<Float?> = _downloadProgress

    private val _currentModelId = MutableStateFlow<String?>(null)
    val currentModelId: StateFlow<String?> = _currentModelId

    private val _statusMessage = MutableStateFlow<String>("Initializing...")
    val statusMessage: StateFlow<String> = _statusMessage

    init {
        loadAvailableModels()
        checkAndLoadDownloadedModel()
    }

    private fun loadAvailableModels() {
        viewModelScope.launch {
            try {
                val models = listAvailableModels()
                _availableModels.value = models

                // Check if any model is already downloaded
                val downloadedModel = models.firstOrNull { it.isDownloaded }
                if (downloadedModel != null && _currentModelId.value == null) {
                    _statusMessage.value =
                        "Model already downloaded. Click to load: ${downloadedModel.name}"
                } else if (_currentModelId.value != null) {
                    _statusMessage.value = "Model loaded! Ready to chat."
                } else {
                    _statusMessage.value = "Ready - Please download and load a model"
                }
            } catch (e: Exception) {
                _statusMessage.value = "Error loading models: ${e.message}"
            }
        }
    }

    private fun checkAndLoadDownloadedModel() {
        viewModelScope.launch {
            try {
                // Give more time for models list to load and SDK to initialize
                delay(1000)

                val downloadedModel = _availableModels.value.firstOrNull { it.isDownloaded }
                if (downloadedModel != null && _currentModelId.value == null) {
                    // Auto-load the downloaded model
                    _statusMessage.value = "Loading ${downloadedModel.name}..."
                    val success = RunAnywhere.loadModel(downloadedModel.id)
                    if (success) {
                        _currentModelId.value = downloadedModel.id
                        _statusMessage.value = "Model loaded! Ready to chat."
                    } else {
                        _statusMessage.value = "Failed to auto-load model. Please load manually."
                    }
                } else if (downloadedModel == null) {
                    _statusMessage.value = "Please download and load a model"
                }
            } catch (e: Exception) {
                // Don't fail silently, show status
                _statusMessage.value = "Auto-load failed. Please load model manually."
            }
        }
    }

    fun downloadModel(modelId: String) {
        viewModelScope.launch {
            try {
                _statusMessage.value = "Downloading model..."
                RunAnywhere.downloadModel(modelId).collect { progress ->
                    _downloadProgress.value = progress
                    _statusMessage.value = "Downloading: ${(progress * 100).toInt()}%"
                }
                _downloadProgress.value = null
                _statusMessage.value = "Download complete! Please load the model."
                // Refresh the models list to update the isDownloaded status
                loadAvailableModels()
            } catch (e: Exception) {
                _statusMessage.value = "Download failed: ${e.message}"
                _downloadProgress.value = null
            }
        }
    }

    fun loadModel(modelId: String) {
        viewModelScope.launch {
            try {
                _statusMessage.value = "Loading model..."
                val success = RunAnywhere.loadModel(modelId)
                if (success) {
                    _currentModelId.value = modelId
                    _statusMessage.value = "Model loaded! Ready to chat."
                } else {
                    _statusMessage.value = "Failed to load model"
                }
            } catch (e: Exception) {
                _statusMessage.value = "Error loading model: ${e.message}"
            }
        }
    }

    fun sendMessage(text: String) {
        // Add user message immediately
        _messages.value += ChatMessage(text, isUser = true)

        viewModelScope.launch {
            _isLoading.value = true

            try {
                // Check if model is loaded, if not show helpful error
                if (_currentModelId.value == null) {
                    // Try to auto-load if there's a downloaded model
                    val downloadedModel = _availableModels.value.firstOrNull { it.isDownloaded }
                    if (downloadedModel != null) {
                        _statusMessage.value = "Auto-loading model..."
                        val success = RunAnywhere.loadModel(downloadedModel.id)
                        if (success) {
                            _currentModelId.value = downloadedModel.id
                            _statusMessage.value = "Model loaded! Generating response..."
                        } else {
                            _messages.value += ChatMessage(
                                "⚠️ Failed to load model. Please load a model manually from the Models menu.",
                                isUser = false
                            )
                            _isLoading.value = false
                            return@launch
                        }
                    } else {
                        _messages.value += ChatMessage(
                            "⚠️ No model is loaded. Please download and load a model first from the Models menu.",
                            isUser = false
                        )
                        _statusMessage.value = "Please load a model"
                        _isLoading.value = false
                        return@launch
                    }
                }

                // Add system instructions to encourage proper mathematical notation
                val systemPrompt = """$text

When answering, use these symbols where relevant: π θ α β × ÷ ² ³ ≤ ≥ ≠ ∫ ∑ √ ∞

Answer:""".trimIndent()

                // Create generation options with higher max tokens for complete responses
                val options = RunAnywhereGenerationOptions(
                    maxTokens = 3072,      // Increased to 3072 to account for system prompt and ensure complete answers
                    temperature = 0.7f,     // Balanced creativity (0.0 = deterministic, 2.0 = very random)
                    topP = 0.9f,           // Nucleus sampling
                    topK = 40              // Top-k sampling
                )

                // Generate response with streaming using the enhanced prompt
                var assistantResponse = ""
                RunAnywhere.generateStream(systemPrompt, options).collect { token ->
                    assistantResponse += token

                    // Update assistant message in real-time
                    val currentMessages = _messages.value.toMutableList()
                    if (currentMessages.lastOrNull()?.isUser == false) {
                        currentMessages[currentMessages.lastIndex] =
                            ChatMessage(assistantResponse, isUser = false)
                    } else {
                        currentMessages.add(ChatMessage(assistantResponse, isUser = false))
                    }
                    _messages.value = currentMessages
                }

                // Update status after successful generation
                _statusMessage.value = "Model loaded! Ready to chat."

            } catch (e: IllegalStateException) {
                // Model not loaded error from SDK
                val errorMsg = if (e.message?.contains("model", ignoreCase = true) == true) {
                    "⚠️ Model error: ${e.message}. Please try loading the model again."
                } else {
                    "⚠️ Error: ${e.message}"
                }
                _messages.value += ChatMessage(errorMsg, isUser = false)
                _statusMessage.value = "Model error - please reload"
                _currentModelId.value = null  // Reset model state

            } catch (e: Exception) {
                val errorMsg = "⚠️ Error generating response: ${e.message ?: "Unknown error"}"
                _messages.value += ChatMessage(errorMsg, isUser = false)
                _statusMessage.value = "Error occurred"
            }

            _isLoading.value = false
        }
    }

    fun refreshModels() {
        loadAvailableModels()
    }
}
