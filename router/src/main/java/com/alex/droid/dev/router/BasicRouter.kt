package com.alex.droid.dev.router

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit

class BasicRouter(
    activity: FragmentActivity,
    container: Int,
    builder: RoutesBuilder
) : Router(activity, container, builder) {

    override fun replace(route: Route, tag: String?) {
        val clazz = routes[route.javaClass]!!
        val fragment = fragmentManager.fragmentFactory.instantiate(activity.classLoader, clazz.name)
        val fragmentTag = tag ?: clazz.simpleName

        fragmentManager.commit {
            replace(container, fragment, fragmentTag)
        }
    }

    override fun replaceWithStack(route: Route, tag: String?, stackName: String?) {
        val clazz = routes[route.javaClass]!!
        val fragment = fragmentManager.fragmentFactory.instantiate(activity.classLoader, clazz.name)
        val fragmentTag = tag ?: clazz.simpleName

        fragmentManager.commit {
            addToBackStack(stackName)
            replace(container, fragment, fragmentTag)
        }
    }
}