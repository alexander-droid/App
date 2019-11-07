package com.alex.droid.dev.app

import android.app.Application
import android.util.Log
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import timber.log.Timber
import java.net.ConnectException

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidLogger()
            androidContext(this@App)
            modules(moduleList)
        }
    }

    fun restartKoin() {
        getKoin().close()
        startKoin()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}