package local.ak.quizseeekers.presentation.nav_graph






const val Quiz_number = "quiz_number"
const val Quiz_category = "quiz_category"
const val Quiz_difficulty = "quiz_difficutly"
const val Quiz_type = "quiz_type"
const val NOQ_KEY = "noq_key"
const val CORRECT_ANS_KEY = "correct_ans_key"





sealed class Routes(val route: String) {

    object Splashscreen : Routes(route = "splash_screen")


    object HomeScreen : Routes(route = "home_screen")



    object QuizScreen : Routes(route = "quiz_screen/{$Quiz_number}/{$Quiz_category}/{$Quiz_difficulty}/{$Quiz_type}"){

        fun PassQuizparam(numofquiz : Int, cateogry : String, diffcutly : String, type : String): String {

            return "quiz_screen/{$Quiz_number}/{$Quiz_category}/{$Quiz_difficulty}/{$Quiz_type}"
                .replace(
                    oldValue = "{$Quiz_number}",
                    newValue = numofquiz.toString()
                )
                .replace(
                    oldValue = "{$Quiz_category}",
                    newValue = cateogry.toString()
                )
                .replace(
                    oldValue = "{$Quiz_difficulty}",
                    newValue = diffcutly.toString()
                )
                .replace(
                    oldValue = "{$Quiz_type}",
                    newValue = type.toString()
                )
        }

    }

    object ScoreScreen : Routes(route = "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}"){

        fun passNumOfQuestionAndCorrectAns(question : Int, correctAnswers : Int): String{
            return "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}"
                .replace("{$NOQ_KEY}", question.toString())
                .replace("{$CORRECT_ANS_KEY}",correctAnswers.toString())
        }
    }

}