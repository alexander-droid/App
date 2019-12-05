package com.alex.droid.dev.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alex.droid.dev.app.model.entity.post.CommentEntity
import com.alex.droid.dev.app.model.entity.post.PostEntity
import com.alex.droid.dev.app.model.entity.user.UserEntity

@Database(
    entities = [PostEntity::class, UserEntity::class, CommentEntity::class],
    version = 16,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract fun feedDao(): FeedDao
    abstract fun userDao(): UserDao
    abstract fun commentDao(): CommentDao
}