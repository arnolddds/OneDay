package com.sobolev.wocab

import com.sobolev.wocab.presentation.WordViewModel
import org.junit.Test
import org.junit.Assert.*

class WordViewModelTest {
    
    @Test
    fun testWordStateInitialization() {
        val viewModel = WordViewModel()
        val word = viewModel.wordState.value
        
        assertEquals("Serendipity", word.word)
        assertEquals("Счастливая случайность", word.translation)
        assertEquals("/ˌserənˈdɪpəti/", word.phonetic)
        assertEquals(2, word.examples.size)
        assertEquals(3, word.otherMeanings.size)
        assertEquals("serendipity", word.audioFileName)
    }
    
    @Test
    fun testPlayingStateChange() {
        val viewModel = WordViewModel()
        
        // Initial state should be false
        assertFalse(viewModel.isPlaying.value)
        
        // Change to true
        viewModel.setPlayingState(true)
        assertTrue(viewModel.isPlaying.value)
        
        // Change to false
        viewModel.setPlayingState(false)
        assertFalse(viewModel.isPlaying.value)
    }
} 