package com.alex.droid.dev.router

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit

class BasicRouter(
    activity: FragmentActivity,
    container: Int,
    builder: RoutesBuilder
) : Router(activity, container, builder) {

    override fun replace(route: Route) {
        val clazz = routes[route.javaClass]!!
        val fragment = fragmentManager.fragmentFactory.instantiate(activity.classLoader, clazz.name).apply {
            arguments = Bundle().apply { putParcelable(KEY_ROUTE, route) }
        }

        fragmentManager.commit {
            replace(container, fragment, route.tag)
        }
    }

    override fun replaceWithStack(route: Route, stackName: String?) {
        val clazz = routes[route.javaClass]!!
        val fragment = fragmentManager.fragmentFactory.instantiate(activity.classLoader, clazz.name).apply {
            arguments = Bundle().apply { putParcelable(KEY_ROUTE, route) }
        }

        fragmentManager.commit {
            addToBackStack(stackName)
            replace(container, fragment, route.tag)
        }
    }
}