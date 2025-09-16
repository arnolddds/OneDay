package com.sobolev.wocab.presentation.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobolev.wocab.domain.usecase.GetDailyContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getDailyContent: GetDailyContentUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<DailyUiState>(DailyUiState.Loading)
    val uiState: StateFlow<DailyUiState> = _uiState.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = DailyUiState.Loading
            runCatching { getDailyContent() }
                .onSuccess { _uiState.value = DailyUiState.Ready(it) }
                .onFailure { _uiState.value = DailyUiState.Error(it.message ?: "Unknown error") }
        }
    }

    fun retry() {
        refresh()
    }

    fun setPlayingState(playing: Boolean) {
        _isPlaying.value = playing
    }
}


