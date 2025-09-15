package com.sobolev.wocab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sobolev.wocab.presentation.DailyInspirationScreen
import com.sobolev.wocab.presentation.DailyInspirationViewModel
import com.sobolev.wocab.ui.theme.OneDayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OneDayTheme {
                val viewModel: DailyInspirationViewModel = viewModel()
                val inspiration by viewModel.inspirationState.collectAsState()
                
                DailyInspirationScreen(inspiration = inspiration)
            }
        }
    }
}

