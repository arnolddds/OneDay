package com.sobolev.wocab.presentation.daily

import com.sobolev.wocab.domain.model.DailyContent

sealed interface DailyUiState {
    data object Loading : DailyUiState
    data class Ready(val content: DailyContent) : DailyUiState
    data class Error(val message: String) : DailyUiState
}


