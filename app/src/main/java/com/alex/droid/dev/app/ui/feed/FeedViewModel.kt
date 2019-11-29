package com.alex.droid.dev.app.ui.feed

import androidx.lifecycle.viewModelScope
import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.model.actions.ActionFeed
import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.app.model.routes.FeedRoute
import com.alex.droid.dev.app.model.routes.PostRoute
import com.alex.droid.dev.app.repository.FeedUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class FeedViewModel: BaseViewModel<FeedRoute>() {
    abstract fun onPostClicked(post: Post)
}

class FeedViewModelImpl(
    private val reedUseCase: FeedUseCase
): FeedViewModel() {

    override fun onCreate() {
        super.onCreate()
    }

    init {
        viewModelScope.launch {
            reedUseCase.execute(ActionFeed()).collect {
                Timber.d("SUCCESS: $it")
            }
        }
    }

    override fun onPostClicked(post: Post) {
        router.replaceWithStack(PostRoute(post = post),  "main")
    }
}