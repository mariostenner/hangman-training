package com.wizeline.academy.hangman.domain

import com.wizeline.academy.hangman.data.HangmanRepository
import com.wizeline.academy.hangman.data.database.entities.toDatabase
import com.wizeline.academy.hangman.domain.model.UserModel
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val repository: HangmanRepository) {
    suspend operator fun invoke(newUser: UserModel){
        repository.insertUserHangman(newUser.toDatabase())
    }
}