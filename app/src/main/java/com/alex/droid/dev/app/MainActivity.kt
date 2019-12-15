package com.alex.droid.dev.app

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alex.droid.dev.app.base.BaseActivity
import com.alex.droid.dev.app.base.EmptyViewModel
import com.alex.droid.dev.app.model.routes.*
import com.alex.droid.dev.app.ui.chat.ChatFragment
import com.alex.droid.dev.app.ui.contacts.ContactsFragment
import com.alex.droid.dev.app.ui.post.feed.FeedFragment
import com.alex.droid.dev.app.ui.post.create.CreatePostFragment
import com.alex.droid.dev.app.ui.post.detail.PostFragment
import com.alex.droid.dev.app.ui.search.SearchFragment
import com.alex.droid.dev.router.EmptyRoute
import com.alex.droid.dev.router.RoutesBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<EmptyViewModel, EmptyRoute>() {

    override fun buildRoutes(): RoutesBuilder =  {
        route(FeedRoute::class to FeedFragment::class)
        route(ContactsRoute::class to ContactsFragment::class)
        route(SearchRoute::class to SearchFragment::class)
        route(PostRoute::class to PostFragment::class)
        route(CreatePostRoute::class to CreatePostFragment::class)
        route(ChatRoute::class to ChatFragment::class)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
                when (f) {
                    is FeedFragment,
                    is ContactsFragment,
                    is SearchFragment -> {
                        bar.animate().translationY(0f)
                        add_btn.show()
                    }
                    else -> {
                        bar.animate().translationY(bar.height.toFloat())
                        add_btn.hide()
                    }
                }
            }
        }, false)

        add_btn.setOnClickListener {
            router.replaceWithStack(CreatePostRoute(), "main")
        }

        bar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_feed -> {
                    router.popInclusive("main")
                    router.replace(FeedRoute)
                }
                R.id.action_contacts -> {
                    router.popInclusive("main")
                    router.replace(ContactsRoute)
                }
                R.id.action_search -> {
                    router.popInclusive("main")
                    router.replace(SearchRoute)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

        if (savedInstanceState == null) {
            bar.selectedItemId = R.id.action_feed
        }
    }
}
