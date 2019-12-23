package com.alex.droid.dev.app.model.routes

import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.router.Route
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostRoute(
    val id: Long? = null,
    val post: Post? = null
): Route