package com.alex.droid.dev.app.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadImage(path: String?) {
    Glide.with(this).load(path)
        .transition(DrawableTransitionOptions().crossFade())
        .into(this)
}

fun ImageView.loadAvatar(path: String?) {
    Glide.with(this).load(path)
        .transition(DrawableTransitionOptions().crossFade())
        .circleCrop()
        .into(this)
}