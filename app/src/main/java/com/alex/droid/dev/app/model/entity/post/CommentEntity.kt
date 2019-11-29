package com.alex.droid.dev.app.model.entity.post

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "comments", indices = [Index(value = ["commentId"], unique = true)])
data class CommentEntity(
    @PrimaryKey
    val commentId: String,
    val commentText: String?,

    @ForeignKey(
        entity = PostEntity::class,
        parentColumns = ["commentId"],
        childColumns = ["postId"],
        onDelete = ForeignKey.CASCADE
    )
    val commentPostId: String
)