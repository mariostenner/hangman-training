package com.wizeline.academy.hangman.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.academy.hangman.domain.GetUsersScoreUseCase
import com.wizeline.academy.hangman.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val getUsersScoreUseCase: GetUsersScoreUseCase
) : ViewModel() {

    val tableScore = MutableStateFlow<List<UserModel>>(listOf())

    fun getScoreTable(){
        viewModelScope.launch {
            tableScore.value = getUsersScoreUseCase()
        }
    }

}