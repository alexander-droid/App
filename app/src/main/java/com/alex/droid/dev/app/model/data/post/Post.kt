package com.alex.droid.dev.app.model.data.post

import android.os.Parcelable
import com.alex.droid.dev.app.model.data.Address
import com.alex.droid.dev.app.model.data.user.User
import com.alex.droid.dev.app.utils.DateUtils
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val id: Long,
    val message: String?,
    val video: String?,
    val address: Address?,
    val image: String?,
    val date: String = DateUtils.currentTimeString(),
    val user: User?,
    val privacy: Privacy?,
    val isLiked: Boolean = false
) : Parcelable


enum class Privacy(val privacy: String) {

    @SerializedName("public")
    PUBLIC("public"),

    @SerializedName("friends")
    FRIENDS("friends"),

    @SerializedName("private")
    PRIVATE("private")
}