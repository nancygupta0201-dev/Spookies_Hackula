package com.runanywhere.startup_hackathon20

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.runanywhere.startup_hackathon20.ui.theme.Startup_hackathon20Theme
import kotlinx.coroutines.launch

class SpookyEdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Startup_hackathon20Theme {
                SpookyEdScreen(this)
            }
        }
    }
}

// JavaScript interface to launch internal game activities
class GameLauncher(private val context: Context) {
    @JavascriptInterface
    fun launchGame(gameNumber: Int) {
        try {
            Log.d("GameLauncher", "Attempting to launch game: $gameNumber")

            val intent = when (gameNumber) {
                1 -> Intent(context, TrickOrAnswerActivity::class.java)
                2 -> Intent(context, PotionBrewingActivity::class.java)
                else -> {
                    Log.e("GameLauncher", "Invalid game number: $gameNumber")
                    return
                }
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            Log.d("GameLauncher", "Successfully launched game: $gameNumber")
        } catch (e: Exception) {
            Log.e("GameLauncher", "Error launching game: $gameNumber", e)
            (context as? ComponentActivity)?.runOnUiThread {
                Toast.makeText(
                    context,
                    "Error launching game: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun SpookyEdScreen(activity: ComponentActivity) {
    var canGoBack by remember { mutableStateOf(false) }
    var webView: WebView? by remember { mutableStateOf(null) }
    var showChatDialog by remember { mutableStateOf(false) }
    var currentPageUrl by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            canGoBack = view?.canGoBack() ?: false
                            currentPageUrl = url
                            Log.d("SpookyEdWebView", "Page finished loading: $url")
                        }

                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            url: String?
                        ): Boolean {
                            Log.d("SpookyEdWebView", "Navigation to: $url")
                            return false
                        }
                    }

                    webChromeClient = object : WebChromeClient() {
                        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                            consoleMessage?.let {
                                Log.d(
                                    "SpookyEdWebView",
                                    "${it.message()} -- From line ${it.lineNumber()} of ${it.sourceId()}"
                                )
                            }
                            return true
                        }
                    }

                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        databaseEnabled = true
                        allowFileAccess = true
                        allowContentAccess = true
                        mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                        // Enable remote debugging
                        WebView.setWebContentsDebuggingEnabled(true)
                    }

                    // Load with loadDataWithBaseURL to ensure proper base URL
                    try {
                        val htmlContent = context.assets.open("home.html").bufferedReader()
                            .use { it.readText() }
                        loadDataWithBaseURL(
                            "file:///android_asset/",
                            htmlContent,
                            "text/html",
                            "UTF-8",
                            null
                        )
                        Log.d("SpookyEdWebView", "Initial home.html loaded successfully")
                    } catch (e: Exception) {
                        Log.e("SpookyEdWebView", "Error loading home.html", e)
                        e.printStackTrace()
                    }

                    addJavascriptInterface(GameLauncher(context), "GameLauncher")
                    webView = this
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Floating Doubt Button - Only show on menu.html
        if (currentPageUrl?.contains("menu.html") == true) {
            FloatingActionButton(
                onClick = { showChatDialog = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = Color(0xFFFF6B35),
                contentColor = Color.White
            ) {
                Text(
                    text = "Doubt ?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }
    }

    // AI Chat Dialog
    if (showChatDialog) {
        AIChatDialog(
            onDismiss = { showChatDialog = false }
        )
    }

    androidx.activity.compose.BackHandler(enabled = canGoBack) {
        webView?.goBack()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIChatDialog(
    onDismiss: () -> Unit,
    viewModel: ChatViewModel = viewModel()
) {
    val messages by viewModel.messages.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val currentModelId by viewModel.currentModelId.collectAsState()
    val statusMessage by viewModel.statusMessage.collectAsState()
    val availableModels by viewModel.availableModels.collectAsState()
    val downloadProgress by viewModel.downloadProgress.collectAsState()

    var inputText by remember { mutableStateOf("") }
    var showModelSelector by remember { mutableStateOf(currentModelId == null) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Auto-scroll to bottom when new messages arrive
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            coroutineScope.launch {
                listState.animateScrollToItem(messages.size - 1)
            }
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2D1B69)
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Header
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFFFF6B35),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Ask AI",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = statusMessage,
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (currentModelId != null) {
                                TextButton(
                                    onClick = { showModelSelector = !showModelSelector },
                                    colors = ButtonDefaults.textButtonColors(
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("Models", fontSize = 12.sp)
                                }
                            }
                            IconButton(onClick = onDismiss) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }

                // Download progress
                downloadProgress?.let { progress ->
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFFFF8C42)
                    )
                }

                // Model Selector
                if (showModelSelector) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFF1A0D3E)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Select a Model",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            
                            if (availableModels.isEmpty()) {
                                Text(
                                    text = "Loading models...",
                                    fontSize = 14.sp,
                                    color = Color.White.copy(alpha = 0.7f)
                                )
                            } else {
                                LazyColumn(
                                    modifier = Modifier.heightIn(max = 200.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    items(availableModels) { model ->
                                        ModelItemCompact(
                                            model = model,
                                            isLoaded = model.id == currentModelId,
                                            onDownload = { 
                                                viewModel.downloadModel(model.id)
                                            },
                                            onLoad = { 
                                                viewModel.loadModel(model.id)
                                                showModelSelector = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Messages List
                if (!showModelSelector || currentModelId != null) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (messages.isEmpty() && currentModelId != null) {
                            item {
                                ChatMessageBubble(
                                    message = ChatMessage(
                                        text = "👻 Hello! I'm your Spooky Ed AI assistant. Ask me anything about the games or any doubts you have!",
                                        isUser = false
                                    )
                                )
                            }
                        }
                        items(messages) { message ->
                            ChatMessageBubble(message)
                        }
                        if (isLoading) {
                            item {
                                TypingIndicator()
                            }
                        }
                    }
                }

                // Input Field
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF1A0D3E),
                    tonalElevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            modifier = Modifier.weight(1f),
                            placeholder = { 
                                Text(
                                    "Type your question...",
                                    color = Color.Gray
                                ) 
                            },
                            enabled = !isLoading && currentModelId != null,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White.copy(alpha = 0.9f),
                                unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                                disabledContainerColor = Color.White.copy(alpha = 0.5f),
                                focusedTextColor = Color(0xFF1A0D3E),
                                unfocusedTextColor = Color(0xFF1A0D3E)
                            ),
                            shape = RoundedCornerShape(24.dp),
                            maxLines = 3
                        )

                        FloatingActionButton(
                            onClick = {
                                if (inputText.isNotBlank()) {
                                    viewModel.sendMessage(inputText)
                                    inputText = ""
                                }
                            },
                            modifier = Modifier.size(48.dp),
                            shape = CircleShape,
                            containerColor = Color(0xFFFF6B35),
                            contentColor = Color.White
                        ) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = "Send"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChatMessageBubble(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier.widthIn(max = 280.dp),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (message.isUser) 16.dp else 4.dp,
                bottomEnd = if (message.isUser) 4.dp else 16.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = if (message.isUser) 
                    Color(0xFFFF6B35) 
                else 
                    Color.White.copy(alpha = 0.9f)
            )
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                color = if (message.isUser) Color.White else Color(0xFF1A0D3E),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun TypingIndicator() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.9f)
            )
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(3) {
                    Surface(
                        modifier = Modifier.size(8.dp),
                        shape = CircleShape,
                        color = Color(0xFFFF6B35)
                    ) {}
                }
            }
        }
    }
}

@Composable
fun ModelItemCompact(
    model: com.runanywhere.sdk.models.ModelInfo,
    isLoaded: Boolean,
    onDownload: () -> Unit,
    onLoad: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isLoaded)
                Color(0xFFFF8C42).copy(alpha = 0.3f)
            else
                Color.White.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = model.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            if (isLoaded) {
                Text(
                    text = "✓ Currently Loaded",
                    fontSize = 12.sp,
                    color = Color(0xFFFF8C42),
                    modifier = Modifier.padding(top = 4.dp)
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onDownload,
                        modifier = Modifier.weight(1f),
                        enabled = !model.isDownloaded,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF6B35),
                            disabledContainerColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            if (model.isDownloaded) "Downloaded" else "Download",
                            fontSize = 12.sp
                        )
                    }

                    Button(
                        onClick = onLoad,
                        modifier = Modifier.weight(1f),
                        enabled = model.isDownloaded,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF8C42),
                            disabledContainerColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Load", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
