package com.alex.droid.dev.app.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

    fun currentTimeString(): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return sdf.format(Date())
    }
}