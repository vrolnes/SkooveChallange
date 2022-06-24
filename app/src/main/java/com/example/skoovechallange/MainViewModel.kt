package com.example.skoovechallange

import androidx.lifecycle.ViewModel
import com.example.skoovechallange.utils.UrlPlayer

class MainViewModel : ViewModel() {
    private var audioPlayer: UrlPlayer? = null

    fun prepareAudio(firstUrl: String, secondUrl: String){
        audioPlayer = UrlPlayer(firstUrl, secondUrl)
        audioPlayer?.prepareAudio()
    }

    fun playOrPauseAudio(){
        audioPlayer?.playOrPauseAudio()
    }

    fun resetAudio(){
        audioPlayer?.resetAudio()
    }
}