package com.alex.droid.dev.app.ui.post.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alex.droid.dev.app.R
import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.ext.clear
import com.alex.droid.dev.app.livedata.SingleLiveEvent
import com.alex.droid.dev.app.model.api.request.RequestCreatePost
import com.alex.droid.dev.app.model.data.Address
import com.alex.droid.dev.app.model.data.post.Privacy
import com.alex.droid.dev.app.model.routes.CreatePostRoute
import com.alex.droid.dev.app.usecase.CreatePostUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber

class CreatePostViewModel(private val createPostUseCase: CreatePostUseCase) : BaseViewModel<CreatePostRoute>() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.v(exception)
    }

    val isEditModeLiveData = MutableLiveData<Boolean>()

    val messageLiveData = MutableLiveData<String>()
    val imageLiveData = MutableLiveData<String>()
    val videoLiveData = MutableLiveData<String>()
    val addressLiveData = MutableLiveData<Address>()
    val privacyLiveData = MutableLiveData<Int>()

    val onSelectMedia = SingleLiveEvent<List<String>>()
    val onSelectLocation = SingleLiveEvent<Address>()

    override fun onCreate() {
        val post = route?.post

        isEditModeLiveData.value = post != null

        messageLiveData.value = post?.message
        imageLiveData.value = post?.image
        addressLiveData.value = post?.address
        privacyLiveData.value = when(post?.privacy) {
            Privacy.PUBLIC -> R.id.privacy_public
            Privacy.FRIENDS -> R.id.privacy_friends
            Privacy.PRIVATE -> R.id.privacy_private
            null -> null
        }
    }

    fun onSelectMediaClicked() {
        onSelectMedia.call()
    }

    fun onSelectLocationClicked() {
        onSelectLocation.call()
    }

    fun onClearMediaClicked() {
        imageLiveData.clear()
    }

    fun onClearLocation() {
        addressLiveData.clear()
    }

    fun onDoneClicked() {
        viewModelScope.launch(handler) {
            Timber.tag("MyCorTest").d("launch")
            createPostUseCase.async(
                RequestCreatePost(
                    message = messageLiveData.value,
                    address = addressLiveData.value,
                    privacy = when(privacyLiveData.value) {
                        R.id.privacy_public -> Privacy.PUBLIC
                        R.id.privacy_friends -> Privacy.FRIENDS
                        R.id.privacy_private -> Privacy.PRIVATE
                        else -> null
                    }
                )
            ).await()

            Timber.tag("MyCorTest").d("launch success")
        }
    }
}