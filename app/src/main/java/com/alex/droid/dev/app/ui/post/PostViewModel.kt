package com.alex.droid.dev.app.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.model.entity.post.PostEntity
import com.alex.droid.dev.app.model.routes.PostRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class PostViewModel: BaseViewModel<PostRoute>() {
    abstract val postLiveData: LiveData<PostEntity>
}

class PostViewModelImpl(): PostViewModel() {

    override val postLiveData = MutableLiveData<PostEntity>()

    override fun onCreate() {
        super.onCreate()
        postLiveData.value = route?.post


        GlobalScope.launch(context = Dispatchers.Default) {  }
    }
}