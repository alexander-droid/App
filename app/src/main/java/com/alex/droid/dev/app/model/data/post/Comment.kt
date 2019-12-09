package com.alex.droid.dev.app.model.data.post

import android.os.Parcelable
import androidx.room.*
import com.alex.droid.dev.app.model.data.user.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val id: String,
    val message: String?,
    val postId: String,
    val userId: String
): Parcelable