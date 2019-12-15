package com.alex.droid.dev.app.ext

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<*>.clear() {
    value = null
}