package com.alex.droid.dev.app.model.data.post

import android.os.Parcelable
import androidx.room.*
import com.alex.droid.dev.app.model.data.user.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val id: String?,
    var text: String?,
    val video: String?,
    val image: String?,
    val date: String?,
    val user: User?,
    val isLiked: Boolean = false
) : Parcelable