package com.wizeline.academy.hangman.utils

import java.util.*
import javax.inject.Inject


class UtilsHelpers @Inject constructor() {

    private var wordWithoutMask : MutableList<MapperDataClass> = mutableListOf()
    private var youWin = false

    fun maskWord(word: String): String {
        wordWithoutMask.removeAll{true}
        youWin =false
        var maskWord = ""
        var withoutMask = ""
        val abc = listOf ("Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M")
        word.uppercase(Locale.getDefault()).map {
            if(abc.contains(it.toString())) {
                maskWord += "_"
                wordWithoutMask.add(
                    MapperDataClass(
                        charToSave = it.toString(),
                        statusChar = false
                    )
                )
            }
            else if(it.toString() == " ") {
                maskWord += " "
                withoutMask += " "
                wordWithoutMask.add(
                    MapperDataClass(
                        charToSave = it.toString(),
                        statusChar = false
                    )
                )
            }else{}
        }
        return maskWord.trim()
    }

    fun compareChar(charToValidate: String): Array<Any> {
        var statusChange = false
            wordWithoutMask.mapIndexed {index, mapperDataClass ->
                if((mapperDataClass.charToSave == charToValidate)){
                    wordWithoutMask[index] = MapperDataClass(mapperDataClass.charToSave,true)
                    statusChange = true
                }else if((mapperDataClass.charToSave==" ")){
                    wordWithoutMask[index] = MapperDataClass(mapperDataClass.charToSave,true)
                }
            }

            // Create new string with the
            var wordAuxReturn = ""
            for(chartItem in wordWithoutMask){
                wordAuxReturn += if(chartItem.charToSave==" ") " "
                else if((chartItem.statusChar === true)&&(chartItem.charToSave!=" ")) chartItem.charToSave
                else{
                    "_"
                }
            }

        val aux = wordWithoutMask.filter { item -> item.statusChar === false}

        if(aux.isEmpty()){youWin = true}

        //if(counterForWin===wordWithoutMask.size-1){youWin = true}
        return arrayOf(wordAuxReturn,statusChange,youWin)
    }

    fun getWordWithoutMask(): String {
        var wordAuxReturn = ""
        for(chartItem in wordWithoutMask) wordAuxReturn += chartItem.charToSave
        return wordAuxReturn
    }

    data class MapperDataClass(val charToSave: String, var statusChar: Boolean)
}