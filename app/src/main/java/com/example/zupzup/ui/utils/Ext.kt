package com.example.zupzup.ui.utils


fun Int.toTimeString(): String {
    var timeString = ""
    val text = this.toString()
    timeString = if (this == 0) {
        "00:00"
    } else if (this < 10) {
        "00:0$this"
    } else if (this < 60) {
        "00:$this"
    } else if (this < 1000) { // 1.00 ~ 9.59
        "0${text.substring(0, 1)}:${text.substring(1, 3)}"
    } else {
        "${text.substring(0, 2)}:${text.substring(2, 4)}"
    }

    return timeString
}

fun String.toPhoneNumberIntFormat(): String {
    return this.replace("-", "")
}

fun String.toPhoneNumberStringFormat(): String {
    if (this.length == 11) {
        return "${this.substring(0, 3)}-${this.substring(3, 7)}-${this.substring(7)}"
    }
    return ""
}