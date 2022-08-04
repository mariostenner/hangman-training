package com.wizeline.academy.hangman.data

import com.google.common.truth.Truth.assertThat
import com.wizeline.academy.hangman.utils.UtilsHelpers
import org.junit.Test

class UtilsHelperTest {

    private val mockMaskWord = UtilsHelpers()
    private val WORD_GAME = "Avenger: Ultron"

    @Test
    fun `test to maskWord using thruth to validate behavior`(){
        val maskedWord = mockMaskWord.maskWord(WORD_GAME)
        assertThat(maskedWord!!).isNotNull()
        assertThat(maskedWord!!).isNotEmpty()
        assertThat(maskedWord!!).startsWith("_")
        assertThat(maskedWord!!).endsWith("_")
    }

}