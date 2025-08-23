package com.sobolev.wocab.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobolev.wocab.data.WordModel
import com.sobolev.wocab.data.WordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WordViewModel : ViewModel() {
    private val _wordState = MutableStateFlow(WordRepository.getWordOfTheDay())
    val wordState: StateFlow<WordModel> = _wordState.asStateFlow()
    
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()
    
    fun setPlayingState(playing: Boolean) {
        _isPlaying.value = playing
    }
} 