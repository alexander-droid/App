package com.alex.droid.dev.app

import android.app.Application
import android.os.Handler
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import timber.log.Timber
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        initTimber()
        startKoin()


        Handler().postDelayed({
            /*val numberStr = "555555555"
            val numberFloat = numberStr.toFloat()
            val fromFloat = NumberFormat.getInstance().format(numberFloat)

            Timber.tag("dgfdg").d("numberStr $numberStr")
            Timber.tag("dgfdg").d("numberFloat $numberFloat")
            Timber.tag("dgfdg").d("fromFloat $fromFloat")*/

            val big1 = BigDecimal.valueOf(5555555555555555555.555555555)
            val big2 = BigDecimal.valueOf(5555555555555555554.555555555)

            val num1 = 5_555_555_555_555
            val num2 = 5_555_555_554.55

            val str = "5555555.55"
            val f = str.toFloat()
            val i = (f * 100).toInt()

//            Timber.tag("dgfdg").i("str $str")
//            Timber.tag("dgfdg").i("f $f")
//            Timber.tag("dgfdg").i("i $i")

            Timber.tag("dgfdg").d("format ${DecimalFormat("0.00").format(num1)}")
            Timber.tag("dgfdg").d("num1 $num1")

//            Timber.tag("dgfdg").v("big1 $big1")
//            Timber.tag("dgfdg").v("big2 $big2")

//            Timber.tag("dgfdg").d("res ${num1 - num2}")
//            Timber.tag("dgfdg").v("res ${big1 - big2}")

        }, 1000)
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