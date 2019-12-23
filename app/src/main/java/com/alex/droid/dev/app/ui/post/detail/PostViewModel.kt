package com.alex.droid.dev.app.ui.post.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.model.api.request.RequestGetPost
import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.app.model.routes.PostRoute
import com.alex.droid.dev.app.usecase.GetPostUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class PostViewModel: BaseViewModel<PostRoute>() {
    abstract val postLiveData: LiveData<Post>
}

class PostViewModelImpl(private val getPostUseCase: GetPostUseCase): PostViewModel() {

    override val postLiveData = MutableLiveData<Post>()

    override fun onCreate() {
        postLiveData.value = route?.post

        val id = route?.id
        if (id != null) {
            viewModelScope.launch {
                Timber.tag("MyCorTest").v("launch")
                val post = getPostUseCase.async(RequestGetPost(id)).await().post
                Timber.tag("MyCorTest").v("launch success")
                postLiveData.value = post
            }
        }
    }
}