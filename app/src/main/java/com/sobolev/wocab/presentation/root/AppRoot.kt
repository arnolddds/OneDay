package com.sobolev.wocab.presentation.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.sobolev.wocab.presentation.ui.PagerScreen
import com.sobolev.wocab.presentation.daily.DailyUiState
import com.sobolev.wocab.presentation.daily.DailyViewModel
import com.sobolev.wocab.presentation.screens.ErrorScreen
import com.sobolev.wocab.presentation.screens.LoadingScreen

@Composable
fun AppRoot(
    viewModel: DailyViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    when (val s = state) {
        is DailyUiState.Loading -> LoadingScreen()
        is DailyUiState.Error -> ErrorScreen(message = s.message, onRetry = { viewModel.retry() })
        is DailyUiState.Ready -> PagerScreen(s.content)
    }
}


