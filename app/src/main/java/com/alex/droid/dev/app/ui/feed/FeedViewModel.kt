package com.alex.droid.dev.app.ui.feed

import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.model.actions.ActionFeed
import com.alex.droid.dev.app.model.entity.PostData
import com.alex.droid.dev.app.model.entity.post.PostEntity
import com.alex.droid.dev.app.model.routes.FeedRoute
import com.alex.droid.dev.app.model.routes.PostRoute
import com.alex.droid.dev.app.paging.PagingData
import com.alex.droid.dev.app.repository.FeedUseCase

abstract class FeedViewModel: BaseViewModel<FeedRoute>() {
    abstract fun onPostClicked(post: PostEntity)
    abstract val pagingData: PagingData<ActionFeed, PostData>
}

class FeedViewModelImpl(
    private val reedUseCase: FeedUseCase
): FeedViewModel() {

    override val pagingData = reedUseCase.execute(ActionFeed())

    override fun onPostClicked(post: PostEntity) {
        router.replaceWithStack(PostRoute(post = post),  "main")
    }
}