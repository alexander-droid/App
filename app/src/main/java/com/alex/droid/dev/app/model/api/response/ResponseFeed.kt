package com.alex.droid.dev.app.model.api.response

import com.alex.droid.dev.app.model.data.post.Post

data class ResponseFeed(
    val list: List<Post> = listOf()
)