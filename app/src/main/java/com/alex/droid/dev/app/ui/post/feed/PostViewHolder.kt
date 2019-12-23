package com.alex.droid.dev.app.ui.post.feed

import android.app.AlertDialog
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.alex.droid.dev.app.ext.loadAvatar
import com.alex.droid.dev.app.ext.loadImage
import com.alex.droid.dev.app.model.data.post.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostViewHolder(private val viewModel: FeedViewModel, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(post: Post) {
        itemView.setOnClickListener {
            viewModel.onPostClicked(post)
        }

        itemView.more_btn.setOnClickListener {
            showMoreDialog(post)
        }

        itemView.avatar_iv.loadAvatar(post.user?.avatar)
        itemView.name_tv.text = post.user?.name
        itemView.date_tv.text = post.date
        itemView.message_tv.text = post.message

        itemView.image.isVisible = post.image != null
        itemView.image.loadImage(post.image)
    }

    private fun showMoreDialog(post: Post) {
        val builder = AlertDialog.Builder(itemView.context);
        builder.setTitle("Select Option");
        builder.setItems(arrayOf("Edit", "Delete")) { _, which ->
            when(which) {
                0 -> viewModel.onEditPostClicked(post)
                1 -> viewModel.onDeletePostClicked(post)
            }
        }
        val alert = builder.create()
        alert.show()
    }
}