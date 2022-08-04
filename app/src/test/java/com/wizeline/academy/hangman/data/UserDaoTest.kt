package com.wizeline.academy.hangman.data


import com.google.common.truth.Truth.assertThat
import com.wizeline.academy.hangman.data.database.dao.UserDao
import com.wizeline.academy.hangman.data.database.entities.UserEntity
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.lang.RuntimeException

class UserDaoTest {
    private val mockDaoScoreTable = MockSingleScoreService()

    @Test
    fun `test getTopTenScores using Thruth to validate behavior`() = runTest{
        val singleResult = mockDaoScoreTable.getTopTenScores()
        assertThat(singleResult).isNotEmpty()
        assertThat(singleResult!![0].score).isNotNull()
    }
}

private class  MockSingleScoreService: UserDao {
    var thrownAnException = false
    private val userScoresTable = (1..20).map { UserEntity(0,"", 0) }

    override suspend fun getTopTenScores(): List<UserEntity> {
        return if(thrownAnException){
            thrownAnException = false
            throw RuntimeException("Houston, we have a problem")
        }else{
            userScoresTable
        }
    }

    override suspend fun insertUser(users: UserEntity){

    }

}