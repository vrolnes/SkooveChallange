package com.example.skoovechallange

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skoovechallange.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var leftAudioPlayer: UrlPlayer? = null
    private var rightAudioPlayer: UrlPlayer? = null

    private var firstRun = true
    private var leftItemIndex = 0
    private var rightItemIndex = 3

    private val imageData = getImageData()
    private val wAVData = getWAVData()

    var mtiState by mutableStateOf("0:0:0")
        private set

    var leftFlashState by mutableStateOf(false)
        private set

    var rightFlashState by mutableStateOf(false)
        private set

    var leftImage by mutableStateOf("")
        private set
    var rightImage by mutableStateOf("")
        private set

    private var currentSecond = 0

    fun prepareAudio() {
        leftAudioPlayer = UrlPlayer {
            prepareLeftResource()
        }
        leftAudioPlayer?.prepareAudio(wAVData[leftItemIndex])
        leftImage = imageData[leftItemIndex]

        rightAudioPlayer = UrlPlayer {
            prepareRightResource()
        }

        rightAudioPlayer?.prepareAudio(wAVData[rightItemIndex])
        rightImage = imageData[rightItemIndex]

        if (firstRun) {
            calculateMTI()
            setLeftFlash()
            setRightFlash()
            firstRun = false
        }

    }

    private fun prepareLeftResource() {
        leftImage = imageData[leftItemIndex]
        leftAudioPlayer?.releasePlayer()
        leftAudioPlayer?.prepareAudio(wAVData[leftItemIndex])
        leftAudioPlayer?.playOrPauseAudio()
    }

    private fun prepareRightResource() {
        rightImage = imageData[rightItemIndex]
        rightAudioPlayer?.releasePlayer()
        rightAudioPlayer?.prepareAudio(wAVData[rightItemIndex])
        rightAudioPlayer?.playOrPauseAudio()
    }

    fun prepareLeftPlayerTrack() {
        leftItemIndex = (leftItemIndex + 1).rem(3)
        leftAudioPlayer?.setNextTrack()
    }

    fun prepareRightPlayerTrack() {
        rightItemIndex = (rightItemIndex + 1).rem(6)
        if (rightItemIndex == 0)
            rightItemIndex = 3
        rightAudioPlayer?.setNextTrack()
    }

    fun playOrPauseAudio() {
        leftAudioPlayer?.playOrPauseAudio()
        rightAudioPlayer?.playOrPauseAudio()
    }

    fun resetAudio() {
        if (leftAudioPlayer?.isPlaying() == true) {
            leftAudioPlayer?.resetAudio()
            rightAudioPlayer?.resetAudio()
            currentSecond = 0
        }
    }

    private fun calculateMTI() {
        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                if (leftAudioPlayer?.isPlaying() == true) {
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
                if (leftAudioPlayer?.isPlaying() == true) {
                    leftAudioPlayer?.getSecond()?.let { second ->
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
                if (leftAudioPlayer?.isPlaying() == true) {
                    leftAudioPlayer?.getSecond()?.let { second ->
                        rightFlashState = isBeat(second)
                    }
                }
                delay(100)
            }
        }
    }
}