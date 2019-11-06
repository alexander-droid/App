package com.alex.droid.dev.app.model.post

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val id: String,
    val text: String?
) : Parcelable