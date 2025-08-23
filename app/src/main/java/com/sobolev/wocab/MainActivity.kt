package com.sobolev.wocab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sobolev.wocab.presentation.WordDayScreen
import com.sobolev.wocab.presentation.WordViewModel
import com.sobolev.wocab.ui.theme.WocabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WocabTheme {
                val viewModel: WordViewModel = viewModel()
                val word by viewModel.wordState.collectAsState()
                
                WordDayScreen(word = word)
            }
        }
    }
}

