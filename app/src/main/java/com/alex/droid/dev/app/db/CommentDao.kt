package com.alex.droid.dev.app.db

import androidx.room.*
import com.alex.droid.dev.app.model.entity.post.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<CommentEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: CommentEntity)

    @Query("SELECT * FROM comments")
    fun users(): Flow<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE commentId = :id")
    fun user(id: String): Flow<CommentEntity>

    @Query("DELETE FROM comments WHERE commentId = :id")
    suspend fun delete(id: String)
}