package com.alex.droid.dev.router

import android.app.Activity
import androidx.fragment.app.Fragment
import com.alex.droid.dev.router.Router.Companion.KEY_ROUTE

fun <T : Route> Fragment.routing(): Lazy<T?> = lazy {
    getRoute<T>()
}

fun <T : Route> Fragment.getRoute(): T? {
    return arguments?.getParcelable(KEY_ROUTE)
}

fun <T : Route> Activity.routing(): Lazy<T?> = lazy {
    getRoute<T>()
}

fun <T : Route> Activity.getRoute(): T? {
    return intent.getParcelableExtra(KEY_ROUTE)
}