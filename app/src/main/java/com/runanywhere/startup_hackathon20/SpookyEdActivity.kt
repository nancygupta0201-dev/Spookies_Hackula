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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.runanywhere.startup_hackathon20.ui.theme.Startup_hackathon20Theme

class SpookyEdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = object : WebViewClient() {
                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                canGoBack = view?.canGoBack() ?: false
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
        }
    }

    androidx.activity.compose.BackHandler(enabled = canGoBack) {
        webView?.goBack()
    }
}
