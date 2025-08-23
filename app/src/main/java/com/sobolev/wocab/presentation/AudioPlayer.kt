package com.sobolev.wocab.presentation

import android.content.Context
import android.media.MediaPlayer
import android.util.Log

class AudioPlayer(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    
    fun playAudio(fileName: String, onComplete: () -> Unit = {}) {
        try {
            // Get the resource ID from the raw folder
            val resourceId = context.resources.getIdentifier(
                fileName, "raw", context.packageName
            )
            
            if (resourceId != 0) {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer.create(context, resourceId)
                mediaPlayer?.setOnCompletionListener {
                    onComplete()
                }
                mediaPlayer?.start()
            } else {
                Log.w("AudioPlayer", "Audio file not found: $fileName")
            }
        } catch (e: Exception) {
            Log.e("AudioPlayer", "Error playing audio: ${e.message}")
        }
    }
    
    fun stopAudio() {
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
        mediaPlayer = null
    }
    
    fun release() {
        stopAudio()
    }
} 