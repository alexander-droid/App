package com.alex.droid.dev.app.ui.post.create

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.alex.droid.dev.app.R
import com.alex.droid.dev.app.base.BaseViewModel
import com.alex.droid.dev.app.ext.clear
import com.alex.droid.dev.app.livedata.SingleLiveEvent
import com.alex.droid.dev.app.model.data.Address
import com.alex.droid.dev.app.model.routes.CreatePostRoute
import timber.log.Timber

class CreatePostViewModel() : BaseViewModel<CreatePostRoute>() {

    val messageLiveData = MutableLiveData<String>()
    val imageLiveData = MutableLiveData<String>()
    val videoLiveData = MutableLiveData<String>()
    val addressLiveData = MutableLiveData<Address>()
    val privacyLiveData = MutableLiveData<Int>()

    val onSelectMedia = SingleLiveEvent<List<String>>()
    val onSelectLocation = SingleLiveEvent<Address>()

    override fun onCreate() {
        //        messageLiveData.value = route?.post?.message
        Handler().postDelayed({ privacyLiveData.value = R.id.post_friends }, 3000)
        imageLiveData.value = route?.post?.image
//        videoLiveData.value = route?.post?.video
//        addressLiveData.value = route?.post?.address

        privacyLiveData.observeForever {
            Timber.d("privacyLiveData $it")
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
        Timber.d("Done ${messageLiveData.value}")
    }
}