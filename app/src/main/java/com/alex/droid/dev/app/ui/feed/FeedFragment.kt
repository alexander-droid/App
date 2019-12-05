package com.alex.droid.dev.app.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alex.droid.dev.app.R
import com.alex.droid.dev.app.base.BaseFragment
import com.alex.droid.dev.app.model.entity.PostData
import com.alex.droid.dev.app.model.routes.FeedRoute
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : BaseFragment<FeedViewModel, FeedRoute>() {

    override fun getLayoutRes() = R.layout.fragment_feed

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = FeedAdapter()
        recyclerView.adapter = adapter

        subscribe(viewModel.pagingData.pagedList) {
            it?.also { adapter.submitList(it) }
        }
    }
}

class FeedAdapter : PagedListAdapter<PostData, PostViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.also { holder.bindPost(it) }
    }
}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val scoreText = itemView.score
    val commentsText = itemView.comments
    val titleText = itemView.title

    fun bindPost(postData: PostData) {
        with(postData) {
            scoreText.text = score.toString()
            commentsText.text = commentCount.toString()
            titleText.text = title
        }
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<PostData>() {
    override fun areItemsTheSame(oldItem: PostData, newItem: PostData): Boolean {
        return oldItem.post?.id == newItem.post?.id
    }

    override fun areContentsTheSame(oldItem: PostData, newItem: PostData): Boolean {
        return oldItem.post?.message == newItem.post?.message
                && oldItem.post?.video == newItem.post?.video
                && oldItem.post?.image == newItem.post?.image
                && oldItem.post?.isLiked == newItem.post?.isLiked
                && oldItem.user?.name == newItem.user?.name
                && oldItem.user?.avatar == newItem.user?.avatar
    }
}