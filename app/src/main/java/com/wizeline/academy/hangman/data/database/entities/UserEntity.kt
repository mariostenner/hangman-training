package com.wizeline.academy.hangman.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wizeline.academy.hangman.domain.model.UserModel

@Entity(tableName = "hangman_users_score")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "username") val username : String,
    @ColumnInfo(name = "score") val score : Int
)


fun UserModel.toDatabase() = UserEntity(id, username, score)