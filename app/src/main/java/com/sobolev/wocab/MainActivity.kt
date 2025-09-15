package com.sobolev.wocab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.sobolev.wocab.presentation.PagerScreen
import com.sobolev.wocab.presentation.daily.DailyUiState
import com.sobolev.wocab.presentation.daily.DailyViewModel
import com.sobolev.wocab.ui.theme.OneDayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OneDayTheme {
                val vm: DailyViewModel = viewModel()
                val state by vm.uiState.collectAsState()
                when (val s = state) {
                    is DailyUiState.Ready -> PagerScreen(s.content)
                    else -> {}
                }
            }
        }
    }
}

