package com.sobolev.wocab.presentation

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.*

class TextToSpeechPlayer(private val context: Context) : TextToSpeech.OnInitListener {
    private var textToSpeech: TextToSpeech? = null
    private var isReady = false
    
    init {
        textToSpeech = TextToSpeech(context, this)
    }
    
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TextToSpeechPlayer", "Language not supported")
            } else {
                isReady = true
                Log.d("TextToSpeechPlayer", "TextToSpeech initialized successfully")
            }
        } else {
            Log.e("TextToSpeechPlayer", "TextToSpeech initialization failed")
        }
    }
    
    fun speak(text: String) {
        if (isReady && textToSpeech != null) {
            textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            Log.d("TextToSpeechPlayer", "Speaking: $text")
        } else {
            Log.w("TextToSpeechPlayer", "TextToSpeech not ready")
        }
    }
    
    fun release() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        textToSpeech = null
        isReady = false
    }
} 