package com.example.edu_fun_2

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Try to launch Spooky Ed app after a brief splash
        setContent {
            SpookyEdLauncherScreen(
                onLaunch = { launchSpookyEd() }
            )
        }
    }

    private fun launchSpookyEd() {
        try {
            val packageName = "com.runanywhere.startup_hackathon20"
            val intent = packageManager.getLaunchIntentForPackage(packageName)

            if (intent != null) {
                startActivity(intent)
                finish() // Close this launcher activity
            } else {
                Toast.makeText(
                    this,
                    "Spooky Ed app not installed. Please install it first.",
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Error launching Spooky Ed: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Composable
fun SpookyEdLauncherScreen(onLaunch: () -> Unit) {
    val spookyPurple = Color(0xFF5a0080)
    val darkPurple = Color(0xFF1a0030)
    val spookyGreen = Color(0xFF00ffd5)

    LaunchedEffect(Unit) {
        delay(1500) // Show splash for 1.5 seconds
        onLaunch()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(spookyPurple, darkPurple)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "🎃",
                fontSize = 100.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = "Spooky Ed",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = spookyGreen,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Educational Halloween Fun",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            CircularProgressIndicator(
                color = spookyGreen,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
