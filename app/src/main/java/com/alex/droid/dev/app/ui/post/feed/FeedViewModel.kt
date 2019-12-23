package com.alex.droid.dev.app.ui.post.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.model.api.request.RequestDeletePost
import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.app.model.routes.CreatePostRoute
import com.alex.droid.dev.app.model.routes.FeedRoute
import com.alex.droid.dev.app.model.routes.PostRoute
import com.alex.droid.dev.app.paging.LoadingState
import com.alex.droid.dev.app.paging.PagingData
import com.alex.droid.dev.app.usecase.FeedUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class FeedViewModel: BaseViewModel<FeedRoute>() {
    abstract fun refresh()
    abstract val feedLiveData: LiveData<PagedList<Post>>
    abstract val loadingState: LiveData<LoadingState>
    abstract val refreshState: LiveData<LoadingState>

    abstract fun onPostClicked(post: Post)
    abstract fun onEditPostClicked(post: Post)
    abstract fun onCreatePostClicked()
    abstract fun onDeletePostClicked(post: Post)
}

class FeedViewModelImpl(
    feedUseCase: FeedUseCase
): FeedViewModel() {

    private val pagingLiveData = MutableLiveData<PagingData<Post>>(feedUseCase.feed())

    override val feedLiveData = pagingLiveData.switchMap { it.pagedList }
    override val loadingState = pagingLiveData.switchMap { it.loadingState }
    override val refreshState = pagingLiveData.switchMap { it.refreshState }

    init {
        Timber.d("init")
    }

    override fun refresh() {
        pagingLiveData.value?.refresh?.invoke()
    }

    override fun onPostClicked(post: Post) {
        router.replaceWithStack(PostRoute(post = post), "main")
    }

    override fun onEditPostClicked(post: Post) {
        router.replaceWithStack(CreatePostRoute(post), "main")
    }

    override fun onDeletePostClicked(post: Post) {
        viewModelScope.launch {
//            deletePostUseCase.async(RequestDeletePost(post.id)).await()
        }
    }

    override fun onCreatePostClicked() {
        router.replaceWithStack(CreatePostRoute(), "main")
    }
}