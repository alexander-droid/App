package com.alex.droid.dev.app.model.data.post

import android.os.Parcelable
import com.alex.droid.dev.app.model.data.user.User
import com.alex.droid.dev.app.utils.DateUtils
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val id: Long,
    val message: String?,
    val video: String?,
    val image: String?,
    val date: String = DateUtils.currentTimeString(),
    val user: User,
    val isLiked: Boolean = false
): Parcelable