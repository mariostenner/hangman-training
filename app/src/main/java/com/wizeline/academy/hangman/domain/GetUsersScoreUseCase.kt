package com.wizeline.academy.hangman.domain

import com.wizeline.academy.hangman.data.HangmanRepository
import com.wizeline.academy.hangman.domain.model.UserModel
import javax.inject.Inject

class GetUsersScoreUseCase @Inject constructor(private val repository: HangmanRepository) {

    suspend operator fun invoke():List<UserModel>{
        return repository.getTopTenScore()
    }
}