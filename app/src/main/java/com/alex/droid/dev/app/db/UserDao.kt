package com.alex.droid.dev.app.db

import androidx.room.*
import com.alex.droid.dev.app.model.entity.user.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM users")
    fun users(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE userId = :id")
    fun user(id: String): Flow<UserEntity>

    @Query("DELETE FROM users WHERE userId = :id")
    suspend fun delete(id: String)
}