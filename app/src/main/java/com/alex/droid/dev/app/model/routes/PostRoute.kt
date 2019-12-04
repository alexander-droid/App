package com.alex.droid.dev.app.model.routes

import com.alex.droid.dev.app.model.entity.post.PostEntity
import com.alex.droid.dev.router.Route
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostRoute(
    val id: String? = null,
    val post: PostEntity? = null
): Route