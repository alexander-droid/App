package com.alex.droid.dev.app.ui.post.detail

import android.os.Bundle
import android.view.View

import com.alex.droid.dev.app.R
import com.alex.droid.dev.app.base.BaseFragment
import com.alex.droid.dev.app.model.routes.PostRoute
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : BaseFragment<PostViewModel, PostRoute>() {

    override fun getLayoutRes() = R.layout.fragment_post

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        subscribe(viewModel.postLiveData) {
            content_tv.text = it?.message
        }
    }
}