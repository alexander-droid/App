package com.alex.droid.dev.app.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.model.actions.ActionFeed
import com.alex.droid.dev.app.model.entity.PostData
import com.alex.droid.dev.app.model.entity.post.PostEntity
import com.alex.droid.dev.app.model.routes.FeedRoute
import com.alex.droid.dev.app.model.routes.PostRoute
import com.alex.droid.dev.app.repository.FeedUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class FeedViewModel: BaseViewModel<FeedRoute>() {
    abstract fun onPostClicked(post: PostEntity)
    abstract val postsLiveData: LiveData<List<PostData>>
}

class FeedViewModelImpl(
    private val reedUseCase: FeedUseCase
): FeedViewModel() {

    override val postsLiveData = MutableLiveData<List<PostData>>()

    override fun onCreate() {
        super.onCreate()
    }

    init {
        requestPosts()
        test()
    }

    private fun requestPosts() = viewModelScope.launch {
        Timber.d("launch")
        reedUseCase.execute(ActionFeed()).collect {
            Timber.d("SUCCESS")
            postsLiveData.value = it
            it.forEach {
                Timber.d("PostData: $it")
            }
        }
    }

    private fun test() = viewModelScope.launch {
        Timber.d("test")
    }

    override fun onPostClicked(post: PostEntity) {
        router.replaceWithStack(PostRoute(post = post),  "main")
    }
}