package com.alex.droid.dev.app.model.data.user

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String?,
    val name: String?,
    val avatar: String?
): Parcelable