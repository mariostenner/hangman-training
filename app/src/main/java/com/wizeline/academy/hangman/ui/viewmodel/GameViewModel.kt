package com.wizeline.academy.hangman.ui.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.academy.hangman.data.network.WordModel
import com.wizeline.academy.hangman.domain.GetWordUseCase
import com.wizeline.academy.hangman.domain.InsertUserUseCase
import com.wizeline.academy.hangman.domain.model.UserModel
import com.wizeline.academy.hangman.utils.UtilsHelpers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val getWordUseCase: GetWordUseCase,
                                        private val utils: UtilsHelpers,
                                        private val insertUserUseCase: InsertUserUseCase) : ViewModel() {
    var wordToGame = MutableStateFlow<String>("")
    private var _movieModel : List<WordModel> = listOf()
    var pointerMovie = MutableStateFlow<Int>(0)


    var counterIntents = MutableStateFlow<Int>(0)
    var totalScore = MutableStateFlow<Int>(0)
    var winRound = MutableStateFlow<Boolean>(false)


    fun getRandomWords() {
        viewModelScope.launch {
            //val wordMasked = maskWord("The man with the mask")//maskWord(getWordUseCase().title)
            _movieModel = getWordUseCase()
            //wordWithoutMask = transformJustUppercase(_movieModel[0].title)
            wordToGame.value = utils.maskWord(_movieModel[pointerMovie.value].title)

            println(_movieModel[0].title.filter { it.toString() != "" })
            println(wordToGame.value)
        }
    }

    fun validateChar(charToValidate: String): MutableStateFlow<String> {
        viewModelScope.launch {
            var status = false
            var (res,res2,res3) = utils.compareChar(charToValidate)
            wordToGame.value = res.toString()
            status = res2 as Boolean
            //validateChanges(status)
            if(status === false) {
                counterIntents.value += 1
                totalScore.value -= 10
            }else{
                totalScore.value += 25
            }
            winRound.value = res3 as Boolean

            if(winRound.value) {
                //pointerMovie.value += 1
                counterIntents.value += 0
            }

        }
        return wordToGame
    }

    fun nextWord(): MutableStateFlow<String>{
       // _movieModel.value = ""
        return wordToGame
    }

    fun hintWord(){
        wordToGame.value = utils.getWordWithoutMask()
        counterIntents.value += 0
    }

    fun clearRound(){

    }

    fun insertScore(username: String){
        viewModelScope.launch {
            insertUserUseCase(UserModel(0,username,totalScore.value))
        }

    }


}