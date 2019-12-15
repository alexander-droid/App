package com.alex.droid.dev.app.ui.post.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.app.model.routes.PostRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class PostViewModel: BaseViewModel<PostRoute>() {
    abstract val postLiveData: LiveData<Post>
}

class PostViewModelImpl(): PostViewModel() {

    override val postLiveData = MutableLiveData<Post>()

    override fun onCreate() {
        postLiveData.value = route?.post


        GlobalScope.launch(context = Dispatchers.Default) {  }
    }
}