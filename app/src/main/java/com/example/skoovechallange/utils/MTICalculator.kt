package com.example.skoovechallange.utils

fun calculateMTI(second: Int?): String? {
    var sixteenths: Int
    var beat: Int
    var bar: Int

    second?.let {
        sixteenths = second / 16
        beat = sixteenths / 4
        bar = beat / 4
        return "$bar:${beat%4}:${sixteenths%4}"
    }
    return null
}