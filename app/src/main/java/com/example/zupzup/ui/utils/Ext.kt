package com.example.zupzup.ui.utils

import java.text.DateFormat
import java.util.*
import java.util.Locale.KOREA


fun Int.toTimeString(): String {
    val text = this.toString()
    val timeString = if (this == 0) {
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

// 22. 12. 19. 오후 3:07
// 22/12/20 오후 3:21
fun Long.toDateFormat(): String {
    val dateTime = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, KOREA)
        .format(Date(this))
    return try {
        val idx = dateTime.indexOf("오후")
        if (idx == -1) {
            dateTime.replace("오전 ", "")
        } else {
            dateTime.substring(0, idx - 1) + " " + (dateTime.substring(
                idx + 3,
                dateTime.indexOf(":")
            )
                .toInt() + 12).toString() + dateTime.substring(dateTime.indexOf(":"))

        }
    } catch (e: Exception) {
        dateTime
    }
}