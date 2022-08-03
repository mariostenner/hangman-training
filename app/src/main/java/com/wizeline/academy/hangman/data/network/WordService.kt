package com.wizeline.academy.hangman.data.network

import com.wizeline.academy.hangman.data.model.MoviesModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.asJavaRandom

class WordService @Inject constructor(private val api: WordApiClient) {
//    suspend fun getWord(): MoviesModel{
//        return withContext(Dispatchers.IO){
//            val response = api.getWord(Random.toString()).body()
//            response ?: MoviesModel(listOf())
//        }
//    }

    fun getWord(): Single<MoviesModel> {
        val random = Random.nextInt(1,30)
        return api.getWord(random.toString())

       // return res as Observable<MoviesModel>
    }
}