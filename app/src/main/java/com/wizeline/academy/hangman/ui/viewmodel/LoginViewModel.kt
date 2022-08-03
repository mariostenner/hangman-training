package com.wizeline.academy.hangman.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.academy.hangman.data.network.MoviesModel
import com.wizeline.academy.hangman.data.network.WordModel
import com.wizeline.academy.hangman.domain.GetUsersScoreUseCase
import com.wizeline.academy.hangman.domain.GetWordUseCase
import com.wizeline.academy.hangman.domain.InsertUserUseCase
import com.wizeline.academy.hangman.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val insertUser: InsertUserUseCase,
    private val getUserScore: GetUsersScoreUseCase) : ViewModel() {

    var totalScore = MutableStateFlow<List<UserModel>>(listOf())

    fun loginGame(newUser: String): MutableStateFlow<List<UserModel>>{
        viewModelScope.launch {
            //insertUser(UserModel(0,newUser,0))
            totalScore = MutableStateFlow<List<UserModel>>(getUserScore())
        }

        return totalScore
    }

}