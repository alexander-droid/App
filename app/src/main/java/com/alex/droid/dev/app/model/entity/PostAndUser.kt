package com.alex.droid.dev.app.model.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.alex.droid.dev.app.model.entity.post.PostEntity
import com.alex.droid.dev.app.model.entity.user.UserEntity

data class PostAndUserEntities(

    @Embedded
    val post: PostEntity?,

    @Relation(
        parentColumn = "postId",
        entityColumn = "userId"
    )
    val user: UserEntity?
)