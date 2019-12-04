package com.alex.droid.dev.app.model.entity.user

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "users", indices = [Index(value = ["userId"], unique = true)])
@Parcelize
data class UserEntity(

    @PrimaryKey
    @ColumnInfo(name = "userId")
    val id: String,

    @ColumnInfo(name = "userName")
    val name: String?,

    @ColumnInfo(name = "userAvatar")
    val avatar: String?
): Parcelable