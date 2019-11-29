package com.alex.droid.dev.app.model.entity.post

import androidx.room.*
import com.alex.droid.dev.app.model.entity.user.UserEntity
import com.alex.droid.dev.app.utils.DateUtils

@Entity(tableName = "posts", indices = [Index(value = ["postId"], unique = true)])
data class PostEntity(

    @PrimaryKey
    @ColumnInfo(name = "postId")
    val postId: String,
    val postMessage: String?,
    val postVideo: String?,
    val postImage: String?,
    val postDate: String = DateUtils.currentTimeString(),

    @ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["postId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )
    val postUserId: String,
    val postIsLiked: Boolean = false
)