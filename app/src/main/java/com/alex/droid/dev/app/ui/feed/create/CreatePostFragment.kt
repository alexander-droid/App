package com.alex.droid.dev.app.ui.feed.create

import android.os.Bundle
import android.view.View
import com.alex.droid.dev.app.R
import com.alex.droid.dev.app.base.BaseFragment
import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.app.model.routes.CreatePostRoute

class CreatePostFragment : BaseFragment<CreatePostViewModel, CreatePostRoute>() {

    override fun getLayoutRes() = R.layout.fragment_create_post

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeNullable(viewModel.postLiveData) { post ->
            if (post == null) {
                initPostCreation()
            } else {
                initPostEdition(post)
            }
        }
    }

    private fun initPostCreation() {

    }

    private fun initPostEdition(post: Post) {

    }
}
