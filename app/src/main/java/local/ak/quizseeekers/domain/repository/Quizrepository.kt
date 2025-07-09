package local.ak.quizseeekers.domain.repository

import local.ak.quizseeekers.domain.model.Quiz

interface Quizrepository {


    suspend fun getQuizzes(amount: Int, category : Int, difficutly: String, type : String): List<Quiz>


}