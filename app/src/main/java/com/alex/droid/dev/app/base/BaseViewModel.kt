package com.alex.droid.dev.app.base

import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.alex.droid.dev.router.Route
import com.alex.droid.dev.router.Router

abstract class BaseViewModel<R: Route> : ViewModel(), LifecycleObserver {

    var isNew = true

    var route: R? = null

    lateinit var router: Router

    open fun onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreateView() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @CallSuper
    open fun onDestroyView() {
        isNew = false
    }

    override fun onCleared() {

    }
}