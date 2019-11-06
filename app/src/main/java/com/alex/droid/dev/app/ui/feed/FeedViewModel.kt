package com.alex.droid.dev.app.ui.feed

import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.model.post.Post
import com.alex.droid.dev.app.model.routes.FeedRoute
import com.alex.droid.dev.app.model.routes.PostRoute

abstract class FeedViewModel: BaseViewModel<FeedRoute>() {
    abstract fun onPostClicked(post: Post)
}

class FeedViewModelImpl(): FeedViewModel() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun onPostClicked(post: Post) {
        router.replaceWithStack(PostRoute(post = post), null, "main")
    }
}
