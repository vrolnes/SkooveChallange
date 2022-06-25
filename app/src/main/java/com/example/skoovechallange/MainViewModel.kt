package com.example.skoovechallange

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skoovechallange.utils.UrlPlayer
import com.example.skoovechallange.utils.calculateMTI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var audioPlayer: UrlPlayer? = null

    var MTIState by mutableStateOf("0:0:0")
    private set

    private var currentSecond = 0

    fun prepareAudio(firstUrl: String, secondUrl: String){
        audioPlayer = UrlPlayer(firstUrl, secondUrl)
        audioPlayer?.prepareAudio()
    }

    fun playOrPauseAudio(){
        audioPlayer?.playOrPauseAudio()
        calculateMTI()
    }

    fun resetAudio(){
        audioPlayer?.resetAudio()
    }

    fun addNextAudio(){

    }

    private fun calculateMTI(){
        viewModelScope.launch(Dispatchers.Default) {
            while (true){
                if (audioPlayer?.isPlaying() == true){
                    audioPlayer?.getSecond()?.let { second ->
                        calculateMTI(second)?.let { mti ->
                            MTIState = mti
                        }
                    }
                }
            }
        }
    }
}