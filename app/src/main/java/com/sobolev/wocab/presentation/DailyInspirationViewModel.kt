package com.sobolev.wocab.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobolev.wocab.data.DailyInspirationModel
import com.sobolev.wocab.data.DailyInspirationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DailyInspirationViewModel : ViewModel() {
    private val _inspirationState = MutableStateFlow(DailyInspirationRepository.getDailyInspiration())
    val inspirationState: StateFlow<DailyInspirationModel> = _inspirationState.asStateFlow()
    
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()
    
    fun setPlayingState(playing: Boolean) {
        _isPlaying.value = playing
    }
} 