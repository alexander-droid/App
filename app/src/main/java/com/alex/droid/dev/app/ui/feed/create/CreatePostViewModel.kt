package com.alex.droid.dev.app.ui.feed.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.model.data.Address
import com.alex.droid.dev.app.model.routes.CreatePostRoute

abstract class CreatePostViewModel: BaseViewModel<CreatePostRoute>() {
    abstract fun onMessageChanged(message: String)
    abstract fun onImagesAttached(images: List<String>)
    abstract fun onVideoAttached(video: String)
    abstract fun onAddressAttached(address: Address)

    abstract val messageLiveData: LiveData<Wrapper<String>>
    abstract val imagesLiveData: LiveData<List<String>>
    abstract val videoLiveData: LiveData<String>
    abstract val addressLiveData: LiveData<Address>
}

class CreatePostViewModelImpl(): CreatePostViewModel() {

    override val messageLiveData = MutableLiveData<Wrapper<String>>()
    override val imagesLiveData = MutableLiveData<List<String>>()
    override val videoLiveData = MutableLiveData<String>()
    override val addressLiveData = MutableLiveData<Address>()

    override fun onCreate() {
        messageLiveData.value = Wrapper(route?.post?.message)
        imagesLiveData.value = route?.post?.images
        videoLiveData.value = route?.post?.video
        addressLiveData.value = route?.post?.address
    }

    override fun onMessageChanged(message: String) {
        messageLiveData.value?.data = message
    }

    override fun onImagesAttached(images: List<String>) {
        imagesLiveData.value = images
    }

    override fun onVideoAttached(video: String) {
        videoLiveData.value = video
    }

    override fun onAddressAttached(address: Address) {
        addressLiveData.value = address
    }
}

data class Wrapper<T>(
    var data: T?
)