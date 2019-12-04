package com.alex.droid.dev.app.model.entity.post

import android.os.Parcelable
import androidx.room.*
import com.alex.droid.dev.app.model.entity.user.UserEntity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "comments", indices = [Index(value = ["commentId"], unique = true)])
@Parcelize
data class CommentEntity(
    @PrimaryKey
    @ColumnInfo(name = "commentId")
    val id: String,

    @ColumnInfo(name = "commentMessage")
    val message: String?,

    @ForeignKey(
        entity = PostEntity::class,
        parentColumns = ["postId"],
        childColumns = ["commentPostId"],
        onDelete = ForeignKey.CASCADE
    )
    @ColumnInfo(name = "commentPostId")
    val postId: String,

    @ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["userId"],
        childColumns = ["commentUserId"],
        onDelete = ForeignKey.CASCADE
    )
    @ColumnInfo(name = "commentUserId")
    val userId: String
): Parcelable