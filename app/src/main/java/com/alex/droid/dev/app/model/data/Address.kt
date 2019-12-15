package com.alex.droid.dev.app.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val lat: Double,
    val lng: Double,
    val name: String,
    val address: String?
) : Parcelable