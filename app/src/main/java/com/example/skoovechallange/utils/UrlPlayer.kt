package com.example.skoovechallange.utils

import android.media.AudioManager
import android.media.MediaPlayer
import java.io.IOException


class UrlPlayer(private var firstUrl: String, private var secondUrl: String) {

    private val firstMediaPlayer = MediaPlayer()
    private val secondMediaPlayer = MediaPlayer()

    fun prepareAudio() {
        // initializing media player


        // below line is use to set the audio
        // stream type for our media player.
        firstMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        secondMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

        // below line is use to set our
        // url to our media player.
        try {
            firstMediaPlayer.setDataSource(firstUrl)
            secondMediaPlayer.setDataSource(secondUrl)
            // below line is use to prepare
            // and start our media player.
            firstMediaPlayer.prepareAsync()
            secondMediaPlayer.prepareAsync()
            firstMediaPlayer.isLooping = true
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun playOrPauseAudio() {
        if (!firstMediaPlayer.isPlaying) {
            firstMediaPlayer.start()
            secondMediaPlayer.start()
        } else {
            firstMediaPlayer.pause()
            secondMediaPlayer.pause()
        }
    }

    fun resetAudio() {
        if (firstMediaPlayer.isPlaying) {
            firstMediaPlayer.pause()
            secondMediaPlayer.pause()
            firstMediaPlayer.seekTo(0)
            secondMediaPlayer.seekTo(0)
        }
    }

    fun getSecond(): Int = firstMediaPlayer.currentPosition / 1000

    fun isPlaying() = firstMediaPlayer.isPlaying
}