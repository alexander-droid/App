package com.alex.droid.dev.app.ui.post.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alex.droid.dev.app.R
import com.alex.droid.dev.app.base.BaseFragment
import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.app.model.routes.FeedRoute
import com.alex.droid.dev.app.paging.LoadingState
import com.alex.droid.dev.app.paging.Status
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.item_post.view.*

class FeedFragment : BaseFragment<FeedViewModel, FeedRoute>() {

    override fun getLayoutRes() = R.layout.fragment_feed

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = FeedAdapter(viewModel)
        recycler_view.adapter = adapter

        subscribe(viewModel.feedLiveData) {
            adapter.submitList(it)
        }

        subscribe(viewModel.loadingState) {
            if (it.status == Status.FAILED) {
//                Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
            }
        }

        subscribe(viewModel.refreshState) {
            swipe_refresh.isRefreshing = it == LoadingState.LOADING
        }

        swipe_refresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }
}