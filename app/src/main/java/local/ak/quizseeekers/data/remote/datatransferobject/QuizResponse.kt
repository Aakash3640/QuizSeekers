package local.ak.quizseeekers.data.remote.datatransferobject

import local.ak.quizseeekers.domain.model.Quiz

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)