package com.alex.droid.dev.app.ext

import android.content.Intent
import android.net.Uri
import timber.log.Timber

fun Intent.log() {

    Timber.tag("INTENT_LOG").d("Data - $dataString")

    val extras = extras
    extras?.keySet()?.forEach { key ->
        Timber.tag("INTENT_LOG").d("Extra - $key: ${extras[key]}")
    }

    val clipData = clipData
    if (clipData != null) {
        for (i in 0 until clipData.itemCount) {
            val item = clipData.getItemAt(i)
            Timber.tag("INTENT_LOG").d("Clipdata - uri: ${item.uri}, text: ${item.text}")
        }
    }
}

fun Intent.fetchImage(): Uri? {
    return data
}