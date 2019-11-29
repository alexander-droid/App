package com.alex.droid.dev.app.model.data.post

import android.os.Parcelable
import com.alex.droid.dev.app.model.data.user.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val id: String?,
    val message: String?,
    val user: User?,
    val isLiked: Boolean = false
) : Parcelable