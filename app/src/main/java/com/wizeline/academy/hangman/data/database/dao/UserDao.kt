package com.wizeline.academy.hangman.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wizeline.academy.hangman.data.database.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM hangman_users_score ORDER BY score DESC LIMIT 10")
    suspend fun getTopTenScores(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(users: UserEntity)


}