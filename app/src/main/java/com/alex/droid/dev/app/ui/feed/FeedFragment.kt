package com.alex.droid.dev.app.ui.feed

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
import com.alex.droid.dev.app.ext.loadImage
import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.app.model.routes.FeedRoute
import com.alex.droid.dev.app.paging.LoadingState
import com.alex.droid.dev.app.paging.Status
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.item_post.view.*
import timber.log.Timber

class FeedFragment : BaseFragment<FeedViewModel, FeedRoute>() {

    override fun getLayoutRes() = R.layout.fragment_feed

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = FeedAdapter()
        recycler_view.adapter = adapter

        subscribe(viewModel.feedLiveData) {
            Timber.d("feedLiveData ${it.size}")
            adapter.submitList(it)
        }

        subscribe(viewModel.loadingState) {
            if (it.status == Status.FAILED) {
                Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
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

class FeedAdapter : PagedListAdapter<Post, GifViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return GifViewHolder(view)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        getItem(position)?.also { holder.bind(it) }
    }
}

class GifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val avatarImage = itemView.avatar_iv

    fun bind(post: Post) {
        avatarImage.loadImage(post.image)
        itemView.message_tv.text = post.message
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}