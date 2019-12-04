package com.alex.droid.dev.app.db

import android.database.Cursor
import androidx.paging.DataSource
import androidx.room.*
import com.alex.droid.dev.app.model.entity.PostData
import com.alex.droid.dev.app.model.entity.post.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: PostEntity)

    @Query("SELECT * FROM posts")
    fun postsByPage(): DataSource.Factory<Int, PostEntity>

    @Transaction
    @Query("SELECT * FROM posts")
    fun postsAndUsers(): Flow<List<PostData>>

    @Query("SELECT * FROM posts WHERE postId = :id")
    fun post(id: String): Flow<PostEntity>

    @Delete(entity = PostEntity::class)
    suspend fun delete(id: String)

    @Query("SELECT * FROM posts")
    fun posts(): Flow<List<PostEntity>>

    @Query("SELECT * FROM posts inner join users on users.userId = posts.postUserId")
    fun postsCursor(): Cursor
}