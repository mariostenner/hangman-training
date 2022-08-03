package com.wizeline.academy.hangman.data.network

import com.wizeline.academy.hangman.data.model.MoviesModel
import com.wizeline.academy.hangman.domain.model.UserModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WordApiClient{
    @GET("popular?api_key=5041c4fbaaa0716596f962c33bb4e197")
//    suspend fun getWord(@Query("page") page : String): Response<MoviesModel>
     fun getWord(@Query("page") page: String): Single<MoviesModel>

     // https://api.themoviedb.org/3/movie/popular?api_key=5041c4fbaaa0716596f962c33bb4e197&page=34
}