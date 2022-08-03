package com.wizeline.academy.hangman.domain

import com.wizeline.academy.hangman.data.HangmanRepository
import com.wizeline.academy.hangman.data.network.WordModel
import javax.inject.Inject

class GetWordUseCase @Inject constructor(private val repository: HangmanRepository){
     suspend operator fun invoke(): List<WordModel>{
         //Get random Movie
         return repository.getMovies().results
     }
}