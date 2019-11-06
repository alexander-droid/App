package com.alex.droid.dev.app.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alex.droid.dev.app.R
import com.alex.droid.dev.router.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseActivity<VM : BaseViewModel<T>, T: Route> : AppCompatActivity() {

    protected lateinit var viewModel: VM

    lateinit var router: Router

    abstract fun buildRoutes(): RoutesBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = BasicRouter(this, R.id.container, buildRoutes())

        viewModel = getViewModel(clazz = getViewModelKClass())

        viewModel.route = getRoute()
        viewModel.router = router

        if (viewModel.isNew) {
            viewModel.onCreate()
        }
        lifecycle.addObserver(viewModel)
    }

    @Suppress("UNCHECKED_CAST")
    protected fun getViewModelKClass(): KClass<VM> {
        val actualClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        return actualClass.kotlin
    }
}