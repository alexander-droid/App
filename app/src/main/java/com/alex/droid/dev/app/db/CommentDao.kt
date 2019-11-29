package com.alex.droid.dev.app.db

import androidx.room.*
import com.alex.droid.dev.app.model.entity.post.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<CommentEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: CommentEntity)

    @Query("SELECT * FROM comments")
    fun users(): Flow<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE commentId = :id")
    fun user(id: String): Flow<CommentEntity>

    @Delete(entity = CommentEntity::class)
    fun delete(id: String)
}