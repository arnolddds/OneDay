package com.sobolev.wocab.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.sobolev.wocab.presentation.daily.DailyUiState
import com.sobolev.wocab.presentation.daily.DailyViewModel
import com.sobolev.wocab.presentation.ui.PagerScreen
import com.sobolev.wocab.presentation.ui.theme.OneDayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OneDayTheme {
                val vm: DailyViewModel = hiltViewModel()
                val state by vm.uiState.collectAsState()
                when (val s = state) {
                    is DailyUiState.Ready -> PagerScreen(s.content)
                    else -> {}
                }
            }
        }
    }
}