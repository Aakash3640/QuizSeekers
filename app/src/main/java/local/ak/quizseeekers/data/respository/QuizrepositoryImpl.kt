package local.ak.quizseeekers.data.respository

import local.ak.quizseeekers.data.remote.QuizApi
import local.ak.quizseeekers.domain.model.Quiz
import local.ak.quizseeekers.domain.repository.Quizrepository

class QuizrepositoryImpl(
    private val quizapi:QuizApi
): Quizrepository {
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficutly: String,
        type: String
    ): List<Quiz> {

        return quizapi.getQiuzzes(amount,category,difficutly,type).results
    }
}