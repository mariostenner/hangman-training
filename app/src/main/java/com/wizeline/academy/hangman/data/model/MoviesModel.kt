package com.wizeline.academy.hangman.data.model

import com.google.gson.annotations.SerializedName
import com.wizeline.academy.hangman.data.network.WordModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

data class MoviesModel(@SerializedName("results") val results: ArrayList<WordModel>)
