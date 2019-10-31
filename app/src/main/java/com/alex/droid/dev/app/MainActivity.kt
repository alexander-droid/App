package com.alex.droid.dev.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.alex.droid.dev.app.ui.contacts.ContactsFragment
import com.alex.droid.dev.app.ui.feed.FeedFragment
import com.alex.droid.dev.app.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_feed -> {
                    supportFragmentManager.popBackStack("main", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.commit {
                        replace(R.id.container, FeedFragment(), FeedFragment::class.simpleName)
                    }
                }
                R.id.action_contacts -> {
                    supportFragmentManager.popBackStack("main", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.commit {
                        replace(R.id.container, ContactsFragment(), ContactsFragment::class.simpleName)
                    }
                }
                R.id.action_search -> {
                    supportFragmentManager.popBackStack("main", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.commit {
                        replace(R.id.container, SearchFragment(), SearchFragment::class.simpleName)
                    }
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        if (savedInstanceState == null) {
            bar.selectedItemId = R.id.action_feed
        }
    }
}
