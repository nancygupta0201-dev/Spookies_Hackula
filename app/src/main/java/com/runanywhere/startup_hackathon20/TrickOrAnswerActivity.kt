package com.runanywhere.startup_hackathon20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runanywhere.startup_hackathon20.ui.theme.Startup_hackathon20Theme

class TrickOrAnswerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Startup_hackathon20Theme {
                TrickOrAnswerScreen(onBackPressed = { finish() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrickOrAnswerScreen(onBackPressed: () -> Unit) {
    val spookyPurple = Color(0xFF5a0080)
    val darkPurple = Color(0xFF1a0030)
    val spookyGreen = Color(0xFF00ffd5)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Trick or Answer",
                        fontWeight = FontWeight.Bold,
                        color = spookyGreen
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = spookyGreen
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF23003e)
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.linearGradient(
                        colors = listOf(darkPurple, spookyPurple, darkPurple)
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
                    fontSize = 80.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Text(
                    text = "Trick or Answer",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = spookyGreen,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Cursive
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Get ready for spooky questions and Halloween fun!",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF40e9ec).copy(alpha = 0.3f)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Game Coming Soon!",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFffd600)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "This is a placeholder for the Trick or Answer game. You can replace this screen with your actual game implementation.",
                            fontSize = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = onBackPressed,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFffd600),
                                contentColor = Color(0xFF3a1550)
                            )
                        ) {
                            Text(
                                "Back to Menu",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
