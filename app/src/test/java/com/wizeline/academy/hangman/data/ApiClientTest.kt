package com.wizeline.academy.hangman.data

import com.google.common.truth.Truth.assertThat
import com.wizeline.academy.hangman.data.model.MoviesModel
import com.wizeline.academy.hangman.data.network.WordApiClient
import com.wizeline.academy.hangman.data.network.WordModel
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import java.lang.RuntimeException


class HangmanRepositoryTest{

    private val mockApiClient = MockSingleWordService()

    @Test
    fun `test getMovies using Thruth to validate behavior`(){
        val singleResult = mockApiClient.getWord("10")
        var result: MoviesModel? = null//ArrayList<WordModel>? = null
        singleResult.subscribe{moviesModel->
            result = moviesModel
        }
        assertThat(result).isNotNull()
        assertThat(result!!.results[0].title).isEqualTo("Love and Thunder")
        assertThat(result!!.results.size).isNotEqualTo(0)

    }
}

private class MockSingleWordService: WordApiClient {

    var thrownAnException = false
    private val testListMovies = MoviesModel((1..20).map {WordModel("Love and Thunder")} as ArrayList<WordModel>)


    override fun getWord(page: String): Single<MoviesModel> {
       return if(thrownAnException){
           thrownAnException = false
           throw RuntimeException("Houston, we have a problem")
       }else{
           Single.just(testListMovies)
       }
    }

}
