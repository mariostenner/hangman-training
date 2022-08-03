package com.wizeline.academy.hangman.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WordApiClient{
    @GET("popular?api_key=5041c4fbaaa0716596f962c33bb4e197")
    suspend fun getWord(@Query("page") page : String): Response<MoviesModel>
}