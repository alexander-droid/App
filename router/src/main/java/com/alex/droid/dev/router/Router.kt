package com.alex.droid.dev.router

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import java.util.HashMap
import kotlin.reflect.KClass

typealias RoutesBuilder = Router.Builder.() -> Unit

abstract class Router(
    protected val activity: FragmentActivity,
    @IdRes protected val container: Int,
    builder: RoutesBuilder
) {

    protected val fragmentManager: FragmentManager = activity.supportFragmentManager

    protected val routes = HashMap<Class<out Route>, Class<out Fragment>>()

    init {
        builder(Builder())
    }

    fun popInclusive(name: String) {
        fragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun pop() {
        fragmentManager.popBackStack()
    }

    abstract fun replace(route: Route)
    abstract fun replaceWithStack(route: Route, stackName: String? = null)

    inner class Builder {

        fun route(routeClass: KClass<out Route>, fragmentClass: KClass<out Fragment>) {
            routes[routeClass.java] = fragmentClass.java
        }

        fun route(pair: Pair<KClass<out Route>, KClass<out Fragment>>) {
            routes[pair.first.java] = pair.second.java
        }
    }

    companion object {
        internal const val TAG = "Navigation"

        internal const val KEY_ROUTE = "${BuildConfig.LIBRARY_PACKAGE_NAME}.Route"
    }
}