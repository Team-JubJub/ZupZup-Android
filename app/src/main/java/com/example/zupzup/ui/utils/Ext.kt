package com.example.zupzup.ui.utils


/*
1. 새벽 12시 ~ 12시 59분 --> 0 ~ 59
2. 새벽 1시 21분 --> 121
3. 오후 5시 -> 1700
 */


fun Int.toTimeString(): String {
    var timeString = ""
    val text = this.toString()
    timeString = if (this < 60) {
        "00:$this"
    } else if (this < 1000) { // 1.00 ~ 9.59
        "0${text.substring(0, 1)}:${text.substring(1, 3)}"
    } else {
        "${text.substring(0, 2)}:${text.substring(2, 4)}"
    }

    return timeString
}

fun String.toPhoneNumberFormat(): String {
    if (this.length == 13) {
        return this.format(
            "%s-%s-%s",
            this.substring(0, 3),
            this.substring(3, 6),
            this.substring(6)
        )
    }
    return ""
}