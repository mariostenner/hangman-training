package com.wizeline.academy.hangman.di

import android.content.Context
import androidx.room.Room
import com.wizeline.academy.hangman.data.database.HangmanDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val HANGMAN_DATABASE_NAME = "hangman_database"

    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, HangmanDatabase::class.java, HANGMAN_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideUsersDao(db: HangmanDatabase) = db.getUsersDao()

}