package com.alex.droid.dev.app.model.entity.post

import android.os.Parcelable
import androidx.room.*
import com.alex.droid.dev.app.model.entity.user.UserEntity
import com.alex.droid.dev.app.utils.DateUtils
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "posts",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["postUserId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["postId"], unique = true)])
@Parcelize
data class PostEntity(

    @PrimaryKey
    @ColumnInfo(name = "postId")
    val id: String,

    @ColumnInfo(name = "postMessage")
    val message: String?,

    @ColumnInfo(name = "postVideo")
    val video: String?,

    @ColumnInfo(name = "postImage")
    val image: String?,

    @ColumnInfo(name = "postDate")
    val date: String = DateUtils.currentTimeString(),

    @ColumnInfo(name = "postUserId")
    val userId: String,

    @ColumnInfo(name = "isPostLiked")
    val isLiked: Boolean = false
): Parcelable