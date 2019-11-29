package com.alex.droid.dev.app.model.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users", indices = [Index(value = ["userId"], unique = true)])
data class UserEntity(

    @PrimaryKey
    @ColumnInfo(name = "userId")
    val userId: String,
    val userName: String?,
    val userAvatar: String?
)