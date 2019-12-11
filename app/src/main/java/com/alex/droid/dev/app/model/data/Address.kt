package com.alex.droid.dev.app.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val lat: Long,
    val lng: Long,
    val address: String?
) : Parcelable