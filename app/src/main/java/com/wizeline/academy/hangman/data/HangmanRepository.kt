package com.wizeline.academy.hangman.data

import com.wizeline.academy.hangman.data.database.dao.UserDao
import com.wizeline.academy.hangman.data.database.entities.UserEntity
import com.wizeline.academy.hangman.data.network.MoviesModel
import com.wizeline.academy.hangman.data.network.WordModel
import com.wizeline.academy.hangman.data.network.WordService
import com.wizeline.academy.hangman.domain.model.UserModel
import com.wizeline.academy.hangman.domain.model.toDomain
import javax.inject.Inject

class HangmanRepository @Inject constructor(
    private val api: WordService,
    private val db: UserDao){

    /**FETCH TO NETWORK**/

    suspend fun getMovies(): MoviesModel{
        return api.getWord()
    }

    /**DATABASE ROOM PROCESS**/

    suspend fun getTopTenScore(): List<UserModel>{
        val response = db.getTopTenScores()
        return response.map { it.toDomain() }
    }

    suspend fun insertUserHangman(username: UserEntity) = db.insertUser(username)


}