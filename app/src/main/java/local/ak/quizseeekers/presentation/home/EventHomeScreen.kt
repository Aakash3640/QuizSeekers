package local.ak.quizseeekers.presentation.home

sealed class EventHomeScreen {



    data class SetnumberofQuiz(val numberofquiz : Int) : EventHomeScreen()
    data class SetQuizcategory(val quizcategory : String) : EventHomeScreen()
    data class SetQuizdiffulty(val quizdiffulty : String) : EventHomeScreen()
    data class SetQuiztype(val quiztype : String) : EventHomeScreen()
}