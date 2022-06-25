package com.example.skoovechallange.utils

import android.media.AudioManager
import android.media.MediaPlayer
import java.io.IOException


class UrlPlayer(private val callback: () -> Unit) {

    private var mediaPlayer: MediaPlayer? = null

    fun prepareAudio(url: String) {
        // initializing media player
        mediaPlayer = MediaPlayer()
        mediaPlayer?.let {
            // below line is use to set the audio
            // stream type for our media player.
            it.setAudioStreamType(AudioManager.STREAM_MUSIC)
            // below line is use to set our
            // url to our media player.
            try {
                it.setDataSource(url)
                // below line is use to prepare
                // and start our media player.
                it.prepare()
                it.isLooping = true
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun setNextTrack() {
        mediaPlayer?.let {
            it.isLooping = false
            it.setOnCompletionListener {
                callback()
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

    fun isPlaying() = mediaPlayer?.isPlaying

    fun releasePlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

}