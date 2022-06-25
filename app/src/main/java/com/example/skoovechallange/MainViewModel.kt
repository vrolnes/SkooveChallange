package com.example.skoovechallange

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skoovechallange.utils.UrlPlayer
import com.example.skoovechallange.utils.calculateMTI
import com.example.skoovechallange.utils.isBar
import com.example.skoovechallange.utils.isBeat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var audioPlayer: UrlPlayer? = null

    var mtiState by mutableStateOf("0:0:0")
        private set

    var leftFlashState by mutableStateOf(false)
        private set

    var rightFlashState by mutableStateOf(false)
        private set

    private var currentSecond = 0

    fun prepareAudio(firstUrl: String, secondUrl: String) {
        audioPlayer = UrlPlayer(firstUrl, secondUrl)
        audioPlayer?.prepareAudio()
        calculateMTI()
        setLeftFlash()
        setRightFlash()
    }

    fun playOrPauseAudio() {
        audioPlayer?.playOrPauseAudio()
    }

    fun resetAudio() {
        if (audioPlayer?.isPlaying() == true) {
            audioPlayer?.resetAudio()
            currentSecond = 0
        }
    }

    private fun calculateMTI() {
        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                if (audioPlayer?.isPlaying() == true) {
                    currentSecond += 1
                    calculateMTI(currentSecond)?.let { mti ->
                        mtiState = mti
                    }
                    delay(100)
                }
            }
        }
    }

    private fun setLeftFlash() {
        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                if (audioPlayer?.isPlaying() == true) {
                    audioPlayer?.getSecond()?.let { second ->
                        leftFlashState = isBar(second)
                    }
                }
                delay(100)
            }
        }
    }

    private fun setRightFlash() {
        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                if (audioPlayer?.isPlaying() == true) {
                    audioPlayer?.getSecond()?.let { second ->
                        rightFlashState = isBeat(second)
                    }
                }
                delay(100)
            }
        }
    }
}