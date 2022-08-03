package com.wizeline.academy.hangman.di

import com.wizeline.academy.hangman.data.network.WordApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // https://api.themoviedb.org/3/movie/popular?api_key=5041c4fbaaa0716596f962c33bb4e197&page=2
    private const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    private const val API_KEY = "5041c4fbaaa0716596f962c33bb4e197"
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): WordApiClient{
        return retrofit.create(WordApiClient::class.java)
    }
}