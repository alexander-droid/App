package com.alex.droid.dev.app.db

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.alex.droid.dev.app.model.entity.PostAndUserEntities
import com.alex.droid.dev.app.model.entity.post.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: PostEntity)

    @Query("SELECT * FROM posts")
    fun postsByPage(): DataSource.Factory<Int, PostEntity>

    @Query("SELECT * FROM posts")
    fun posts(): Flow<List<PostEntity>>

    @Query("SELECT * FROM posts inner join users on users.userId = posts.postUserId")
    fun postsCursor(): Cursor

    @Transaction
    @Query("SELECT * FROM posts")
    fun postsAndUsers(): List<PostAndUserEntities>

    @Query("SELECT * FROM posts WHERE postId = :id")
    fun post(id: String): LiveData<PostEntity>

    @Delete(entity = PostEntity::class)
    fun delete(id: String)
}