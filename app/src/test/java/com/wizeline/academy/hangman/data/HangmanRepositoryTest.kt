package com.wizeline.academy.hangman.data

import com.google.common.truth.Truth.assertThat
import com.wizeline.academy.hangman.data.model.MoviesModel
import com.wizeline.academy.hangman.data.network.WordModel
import com.wizeline.academy.hangman.data.network.WordService
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.Mock

class HangmanRepositoryTest (private val wordService: WordService){

    @Test
    fun `test getMovies using Thruth to validate behavior`(){
        val singleResult = wordService.getWord()
        var result: MoviesModel? = null//ArrayList<WordModel>? = null
        singleResult.subscribe{moviesModel->
            result = moviesModel
        }
        assertThat(result).isNotNull()
        assertThat(result!!.results).isEqualTo("results")
    }



}