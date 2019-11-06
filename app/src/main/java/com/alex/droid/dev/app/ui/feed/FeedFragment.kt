package com.alex.droid.dev.app.ui.feed

import android.os.Bundle
import android.view.View
import com.alex.droid.dev.app.R
import com.alex.droid.dev.app.base.BaseFragment
import com.alex.droid.dev.app.model.post.Post
import com.alex.droid.dev.app.model.routes.FeedRoute
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : BaseFragment<FeedViewModel, FeedRoute>() {

    override fun getLayoutRes() = R.layout.fragment_feed

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn.setOnClickListener {
            viewModel.onPostClicked(Post("id", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat"))
        }
    }
}
