package com.example.skoovechallange.utils

import com.example.skoovechallange.data.Constants

fun getImageData(): MutableList<String> {
    val list = mutableListOf<String>()
    list.let {
        it.add(Constants.aOneJPG)
        it.add(Constants.aTwoJPG)
        it.add(Constants.aThreeJPG)
        it.add(Constants.bOneJPG)
        it.add(Constants.bTwoJPG)
        it.add(Constants.bThreeJPG)
    }
    return list
}

fun getWAVData(): MutableList<String> {
    val list = mutableListOf<String>()
    list.let {
        it.add(Constants.aOneWAV)
        it.add(Constants.aTwoWAV)
        it.add(Constants.aThreeWAV)
        it.add(Constants.bOneWAV)
        it.add(Constants.bTwoWAV)
        it.add(Constants.bThreeWAV)
    }
    return list
}