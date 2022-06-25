package com.example.skoovechallange.utils

import android.media.MediaPlayer
import java.io.IOException


class UrlPlayer(private val playFinishCallback: () -> Unit) {

    private var mediaPlayer: MediaPlayer? = null

    fun prepareAudio(url: String) {
        // initializing media player
        mediaPlayer = MediaPlayer()
        mediaPlayer?.let {
            try {
                it.setDataSource(url)
                it.prepare()
                it.isLooping = true
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun setNextTrack() { //cancel outs the loop and prepares play finished callback
        mediaPlayer?.let {
            it.isLooping = false
            it.setOnCompletionListener {
                playFinishCallback()
            }
        }
    }

    fun playOrPauseAudio() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()
            } else {
                it.pause()
            }
        }
    }

    fun resetAudio() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                it.seekTo(0)
            }
        }
    }

    fun getSecond(): Int? = mediaPlayer?.currentPosition?.div(1000)

    fun isPlaying(): Boolean {
        return try {
            mediaPlayer?.isPlaying!!
        } catch (exception: Exception) {
            false
        }
    }

    fun releasePlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

}