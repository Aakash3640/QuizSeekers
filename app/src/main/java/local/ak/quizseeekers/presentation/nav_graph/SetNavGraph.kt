    package local.ak.quizseeekers.presentation.nav_graph

    import android.widget.Toast
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.collectAsState
    import androidx.compose.runtime.getValue
    import androidx.compose.ui.platform.LocalContext
    import androidx.hilt.navigation.compose.hiltViewModel
    import androidx.navigation.NavType
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import androidx.navigation.navArgument
    import local.ak.SplashScreen
    import local.ak.quizseeekers.presentation.home.HomeScreen
    import local.ak.quizseeekers.presentation.home.HomeScreenViewModal
    import local.ak.quizseeekers.presentation.quiz.QuizScreen
    import local.ak.quizseeekers.presentation.quiz.QuizState
    import local.ak.quizseeekers.presentation.quiz.QuizViewModel
    import local.ak.quizseeekers.presentation.score.ScoreScreen


    @Composable
    fun SetNavGraph() {

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Routes.Splashscreen.route){

            composable(route = Routes.Splashscreen.route) {
               SplashScreen(navController)
            }

            composable(route = Routes.HomeScreen.route){
                val viewModel : HomeScreenViewModal = hiltViewModel()
                val state by viewModel.homestate.collectAsState()
                HomeScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navController = navController

                )
            }

            composable(route = Routes.QuizScreen.route,
                    arguments = listOf(
                        navArgument(Quiz_number){type = NavType.IntType},
                        navArgument(Quiz_category){type = NavType.StringType},
                        navArgument(Quiz_difficulty){type = NavType.StringType},
                        navArgument(Quiz_type){type = NavType.StringType},

                    )
                ){

                    val numofquiz = it.arguments?.getInt(Quiz_number)
                    val category = it.arguments?.getString(Quiz_category)
                    val diffcutly = it.arguments?.getString(Quiz_difficulty)
                    val type = it.arguments?.getString(Quiz_type)

                val quizViewModel : QuizViewModel = hiltViewModel()
                val state by quizViewModel.quizlist.collectAsState()

                QuizScreen(

                    numOfQuiz = numofquiz!!,
                    quizCategory = category!!,
                    quizdiffculty = diffcutly!!,
                    quizType = type!!,
                    event = { quizViewModel.onEvent(it) },
                    state = state,
                    navController = navController

                )

            }



            composable(route = Routes.ScoreScreen.route,
                arguments = listOf(
                    navArgument(NOQ_KEY){type = NavType.IntType},
                    navArgument(CORRECT_ANS_KEY){type = NavType.IntType},

                    ),

                ){

                val numOfQuestions = it.arguments?.getInt(NOQ_KEY)
                val numOfCorrectAns = it.arguments?.getInt(CORRECT_ANS_KEY)



                ScoreScreen(
                    numofQuestion = numOfQuestions!!,
                    numofCorrectAns = numOfCorrectAns!!,
                    navController = navController
                )




            }
        }
    }