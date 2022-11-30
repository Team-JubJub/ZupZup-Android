package com.example.zupzup.ui.utils

fun Int.toTimeString(): String {
    var text = ""
    val time = this.toString()
    if (this > 0) {
        val minutes = time.substring(time.length - 2, time.length)
        val hour = time.substring(0, time.length - 2)
        text = "$hour:$minutes"
    }
    if (this in 1..999) {
        text = "0$text"
    }

    return text
}

fun String.toPhoneNumberFormat() : String {
    if(this.length == 13) {
        return this.format("%s-%s-%s", this.substring(0,3), this.substring(3,6), this.substring(6))
    }
    return ""
}