package com.alex.droid.dev.app.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.alex.droid.dev.app.ext.loadImage

@BindingAdapter("srcUrl")
fun loadImageUrl(imageView: ImageView, imageUrl: String?) {
    imageView.loadImage(imageUrl)
}