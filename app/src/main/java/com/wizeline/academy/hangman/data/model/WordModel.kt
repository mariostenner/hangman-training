package com.wizeline.academy.hangman.data.network

import com.google.gson.annotations.SerializedName

data class WordModel(@SerializedName("title") val title: String)

//https://api.themoviedb.org/3/movie/popular?api_key=5041c4fbaaa0716596f962c33bb4e197&page=2