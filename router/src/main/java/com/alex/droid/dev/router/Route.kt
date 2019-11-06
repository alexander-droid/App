package com.alex.droid.dev.router

import android.os.Parcelable

interface Route: Parcelable { //TODO abstract class?
    val tag: String
        get() = javaClass.simpleName
}