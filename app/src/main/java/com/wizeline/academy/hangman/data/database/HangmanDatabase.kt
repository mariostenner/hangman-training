package com.wizeline.academy.hangman.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wizeline.academy.hangman.data.database.dao.UserDao
import com.wizeline.academy.hangman.data.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class HangmanDatabase: RoomDatabase() {
    abstract fun getUsersDao(): UserDao
}