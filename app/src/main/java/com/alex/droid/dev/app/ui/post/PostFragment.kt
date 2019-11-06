package com.alex.droid.dev.app.ui.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alex.droid.dev.app.R
import com.alex.droid.dev.app.base.BaseFragment
import com.alex.droid.dev.app.model.routes.PostRoute
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : BaseFragment<PostViewModel, PostRoute>() {

    override fun getLayoutRes() = R.layout.fragment_post

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        subscribe(viewModel.postLiveData) {
            content_tv.text = it?.text
        }
    }
}