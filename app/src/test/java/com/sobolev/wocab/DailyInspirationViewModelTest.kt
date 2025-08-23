package com.sobolev.wocab

import com.sobolev.wocab.presentation.DailyInspirationViewModel
import org.junit.Test
import org.junit.Assert.*

class DailyInspirationViewModelTest {
    
    @Test
    fun testInspirationStateInitialization() {
        val viewModel = DailyInspirationViewModel()
        val inspiration = viewModel.inspirationState.value
        
        // Test movie
        assertEquals("The Shawshank Redemption", inspiration.movie.title)
        assertEquals(1994, inspiration.movie.year)
        
        // Test book
        assertEquals("1984", inspiration.book.title)
        assertEquals("George Orwell", inspiration.book.author)
        
        // Test quote
        assertEquals("The only way to do great work is to love what you do.", inspiration.quote.text)
        assertEquals("Steve Jobs", inspiration.quote.author)
        
        // Test word
        assertEquals("Serendipity", inspiration.word.word)
        assertEquals("Счастливая случайность", inspiration.word.translation)
        assertEquals("/ˌserənˈdɪpəti/", inspiration.word.phonetic)
        assertEquals(2, inspiration.word.examples.size)
        assertEquals(3, inspiration.word.otherMeanings.size)
    }
    
    @Test
    fun testPlayingStateChange() {
        val viewModel = DailyInspirationViewModel()
        
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