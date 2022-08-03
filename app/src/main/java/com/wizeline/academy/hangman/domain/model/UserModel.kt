package com.wizeline.academy.hangman.domain.model

import com.wizeline.academy.hangman.data.database.dao.UserDao
import com.wizeline.academy.hangman.data.database.entities.UserEntity

data class UserModel(val id: Int, val username: String, val score: Int)

fun UserEntity.toDomain() = UserModel(id, username, score)
