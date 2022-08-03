package com.wizeline.academy.hangman.domain

import com.wizeline.academy.hangman.data.HangmanRepository
import com.wizeline.academy.hangman.data.model.MoviesModel
import com.wizeline.academy.hangman.data.network.WordModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetWordUseCase @Inject constructor(private val repository: HangmanRepository){

     fun getWord(): Single<MoviesModel> {
         //Get random Movie
         return repository.getMovies()
     }

}