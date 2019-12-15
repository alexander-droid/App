package com.alex.droid.dev.app.ui.post.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.app.model.routes.FeedRoute
import com.alex.droid.dev.app.paging.LoadingState
import com.alex.droid.dev.app.paging.PagingData
import com.alex.droid.dev.app.usecase.FeedUseCase

abstract class FeedViewModel: BaseViewModel<FeedRoute>() {
    abstract fun refresh()
    abstract val feedLiveData: LiveData<PagedList<Post>>
    abstract val loadingState: LiveData<LoadingState>
    abstract val refreshState: LiveData<LoadingState>
}

class FeedViewModelImpl(reedUseCase: FeedUseCase): FeedViewModel() {

    private val pagingLiveData = MutableLiveData<PagingData<Post>>(reedUseCase.feed())

    override val feedLiveData = pagingLiveData.switchMap { it.pagedList }
    override val loadingState = pagingLiveData.switchMap { it.loadingState }
    override val refreshState = pagingLiveData.switchMap { it.refreshState }

    override fun refresh() {
        pagingLiveData.value?.refresh?.invoke()
    }
}