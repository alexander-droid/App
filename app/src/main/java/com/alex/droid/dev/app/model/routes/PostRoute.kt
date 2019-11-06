package com.alex.droid.dev.app.model.routes

import com.alex.droid.dev.app.model.post.Post
import com.alex.droid.dev.router.Route
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostRoute(
    val id: String? = null,
    val post: Post? = null
): Route