package com.example.skoovechallange.utils

fun calculateMTI(second: Int?): String? {
    var sixteenths: Int
    var beat: Int
    var bar: Int

    second?.let {
        sixteenths = second / 16
        beat = sixteenths / 4
        bar = beat / 4
        return "$bar:${beat.rem(4)}:${sixteenths.rem(4)}"
    }
    return null
}

fun isBar(second: Int?): Boolean {
    second?.let {
        if (it.rem(4) == 0){
            return true
        }
    }
    return false
}

fun isBeat(second: Int?): Boolean {
    second?.let {
        if (it.rem(2) == 0){
            return true
        }
    }
    return false
}