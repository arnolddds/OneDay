package com.sobolev.wocab.presentation.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

class TtsController(
    private val appContext: Context,
    private var locale: Locale = Locale.US
) {
    private var tts: TextToSpeech? = null

    init {
        initialize()
    }

    private fun initialize() {
        tts = TextToSpeech(appContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(locale)
                val supported = result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED
                if (!supported) {
                    Toast.makeText(appContext, "Selected language not supported", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun setLocale(newLocale: Locale) {
        locale = newLocale
        tts?.setLanguage(locale)
    }

    fun speak(text: String) {
        val engine = tts ?: return
        val availability = engine.isLanguageAvailable(locale)
        if (availability == TextToSpeech.LANG_MISSING_DATA || availability == TextToSpeech.LANG_NOT_SUPPORTED) {
            // Language not supported
            return
        }
        stop()
        engine.speak(text, TextToSpeech.QUEUE_FLUSH, null, text.hashCode().toString())
    }

    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}

@Composable
fun rememberTtsController(initialLocale: Locale = Locale.US): TtsController {
    val context = LocalContext.current.applicationContext
    val controller = remember(initialLocale) { TtsController(context, initialLocale) }
    DisposableEffect(controller) {
        onDispose {
            controller.shutdown()
        }
    }
    return controller
}


