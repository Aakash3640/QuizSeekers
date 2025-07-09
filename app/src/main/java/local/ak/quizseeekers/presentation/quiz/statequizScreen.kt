package local.ak.quizseeekers.presentation.quiz

import local.ak.quizseeekers.domain.model.Quiz

data class statequizScreen(

    val isLoading : Boolean = false,
    val quizstate : List<QuizState> = emptyList(),
    val error : String = "",
    val score : Int = 0
)


data class QuizState(

    val quiz  : Quiz? = null,
    val shuffledoption : List<String> = emptyList(),
    val selectedoption : Int ? = -1
)