package local.ak.quizseeekers.presentation.quiz

sealed class EventQuizScreen{

    data class GetQuizzes(val numofquiz : Int, val category: Int, val difficlty : String, val type : String): EventQuizScreen()

    data class SetoptionSelected(val quizStateIndex : Int, val selectedOption: Int): EventQuizScreen()
}

