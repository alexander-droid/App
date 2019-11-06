package com.alex.droid.dev.app.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.model.post.Post
import com.alex.droid.dev.app.model.routes.PostRoute

abstract class PostViewModel: BaseViewModel<PostRoute>() {
    abstract val postLiveData: LiveData<Post>
}

class PostViewModelImpl(): PostViewModel() {

    override val postLiveData = MutableLiveData<Post>()

    override fun onCreate() {
        super.onCreate()
        postLiveData.value = route?.post
    }
}