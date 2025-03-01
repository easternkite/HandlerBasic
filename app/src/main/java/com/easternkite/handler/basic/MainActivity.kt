package com.easternkite.handler.basic

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.easternkite.handler.basic.ui.theme.HandlerbasicTheme
import java.lang.Thread.sleep

class MainActivity : ComponentActivity() {
    private var isActive = true
    private lateinit var mainHandler: Handler
    private val viewModel : HandlerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainHandler = Handler(mainLooper)
        startTimer()
        enableEdgeToEdge()
        setContent {
            val currentTime by viewModel.uiState.collectAsState()
            HandlerbasicTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Button({}, modifier = Modifier.padding(innerPadding)) {
                        Text(currentTime)
                    }
                }
            }
        }
    }

    private fun startTimer() {
        Thread {
            while (isActive) {
                sleep(1000L)
                mainHandler.post {
                    viewModel.dispatchEvent()
                }
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        isActive = false
    }
}
