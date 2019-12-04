package com.alex.droid.dev.app.ui.feed

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.alex.droid.dev.app.R
import com.alex.droid.dev.app.base.BaseFragment
import com.alex.droid.dev.app.model.routes.FeedRoute
import timber.log.Timber

class FeedFragment : BaseFragment<FeedViewModel, FeedRoute>() {

    override fun getLayoutRes() = R.layout.fragment_feed

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.postsLiveData.observe(viewLifecycleOwner, Observer {
            Timber.d("Data: ${it.size}, ${Thread.currentThread()}")
        })
    }
}