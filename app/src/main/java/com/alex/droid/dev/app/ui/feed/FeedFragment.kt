package com.alex.droid.dev.app.ui.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit

import com.alex.droid.dev.app.R
import com.alex.droid.dev.app.ui.post.PostFragment
import kotlinx.android.synthetic.main.fragment_feed.*

/**
 * A simple [Fragment] subclass.
 */
class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                addToBackStack("main")
                replace(R.id.container, PostFragment(), PostFragment::class.simpleName)
            }
        }
    }
}
