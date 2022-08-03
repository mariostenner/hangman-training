package com.wizeline.academy.hangman.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class WordService @Inject constructor(private val api: WordApiClient) {
    suspend fun getWord(): MoviesModel{
        return withContext(Dispatchers.IO){
            val response = api.getWord(Random.toString()).body()
            response ?: MoviesModel(listOf())
        }
    }
}