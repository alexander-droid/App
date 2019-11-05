package com.alex.droid.dev.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alex.droid.dev.app.model.routes.*
import com.alex.droid.dev.app.ui.chat.ChatFragment
import com.alex.droid.dev.app.ui.contacts.ContactsFragment
import com.alex.droid.dev.app.ui.feed.FeedFragment
import com.alex.droid.dev.app.ui.post.PostFragment
import com.alex.droid.dev.app.ui.search.SearchFragment
import com.alex.droid.dev.router.BasicRouter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val router = BasicRouter(this, R.id.container) {
        route(FeedRoute::class to FeedFragment::class)
        route(ContactsRoute::class to ContactsFragment::class)
        route(SearchRoute::class to SearchFragment::class)
        route(PostRoute::class to PostFragment::class)
        route(ChatRoute::class to ChatFragment::class)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
