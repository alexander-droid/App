package com.alex.droid.dev.app.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.alex.droid.dev.router.Route
import com.alex.droid.dev.router.getRoute
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ViewModelParameters
import org.koin.androidx.viewmodel.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseFragment<VM : BaseViewModel<R>, R: Route> : Fragment() {

    protected lateinit var baseActivity: BaseActivity<*, *>

    protected lateinit var viewModel: VM

    abstract fun getLayoutRes(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as BaseActivity<*, *>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getKoin().getViewModel(
            ViewModelParameters(
                clazz = getViewModelKClass(),
                owner = this
            )
        )

        viewModel.route = getRoute()

        if (viewModel.isNew) {
            viewModel.onCreate()
        }
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewLifecycleOwner.lifecycle.addObserver(viewModel)
        return inflater.inflate(getLayoutRes(), null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.router = baseActivity.router
    }

    @Suppress("UNCHECKED_CAST")
    protected fun getViewModelKClass(): KClass<VM> {
        val actualClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        return actualClass.kotlin
    }

    protected fun <T> subscribe(liveData: (LiveData<T>)?, onNext: (t: T) -> Unit) {
        liveData?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                onNext(it)
            }
        })
    }

    protected fun <T> subscribeNullable(liveData: LiveData<T>, onNext: (t: T?) -> Unit) {
        liveData.observe(viewLifecycleOwner, Observer { onNext(it) })
    }
}