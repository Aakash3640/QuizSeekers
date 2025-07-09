package local.ak.quizseeekers.presentation.quiz

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.pm.ActivityInfo
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import local.ak.quizseeekers.R
import local.ak.quizseeekers.presentation.common.ButtonBox
import local.ak.quizseeekers.presentation.common.QuizAppBar
import local.ak.quizseeekers.presentation.nav_graph.Routes
import local.ak.quizseeekers.presentation.quiz.component.QuizInterface
import local.ak.quizseeekers.presentation.quiz.component.QuizShimmerScreen
import local.ak.quizseeekers.presentation.util.Constants
import local.ak.quizseeekers.presentation.util.Dimens
import local.ak.quizseeekers.presentation.util.Dimens.MediumPadding



//@Preview
//@Composable
//fun Prevquiz(){
//
//    val navController = rememberNavController()
//    QuizScreen(
//        numOfQuiz = 12,
//        quizCategory = "GK",
//        quizdiffculty = "Eazy",
//        quizType = "Multiple Choice",
//        event = {},
//        state = statequizScreen(),
//        navController = navController
//
//        )
//}

@SuppressLint("ContextCastToActivity")
@Composable
fun LockScreenOrientation(orientation: Int) {
    val activity = LocalContext.current as? Activity
    DisposableEffect(Unit) {
        val originalOrientation = activity?.requestedOrientation
        activity?.requestedOrientation = orientation

        onDispose {
            // Restore the original orientation when leaving the screen
            activity?.requestedOrientation = originalOrientation
                ?: ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }
}


@Composable
fun QuizScreen (
    numOfQuiz: Int,
    quizCategory: String,
    quizdiffculty: String,
    quizType: String,
    event: (EventQuizScreen)-> Unit,
    state: statequizScreen,
    navController: NavController,
    context: Context = LocalContext.current,



){
    
LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    BackHandler {

        navController.navigate(Routes.HomeScreen.route){
            popUpTo(Routes.HomeScreen.route){inclusive = true}
        }

    }


    LaunchedEffect(key1 = Unit) {



        val difficulty = when(quizdiffculty){
            "Medium" -> "medium"
            "Hard" -> "hard"
            else -> "easy"
        }

        val type = when(quizType){
            "Multiple Choice" -> "multiple"
            else -> "boolean"
        }
        event(EventQuizScreen.GetQuizzes(numOfQuiz, Constants.categoriesMap[quizCategory]!!,difficulty,type))

    }


    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
    ){

        QuizAppBar(quizcategory = quizCategory) {

            navController.navigate(Routes.HomeScreen.route){
                popUpTo(Routes.HomeScreen.route){inclusive = true}
            }
        }




    Column (

        modifier = Modifier
            .padding(Dimens.SmallPadding)
            .fillMaxWidth()
    ){

        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
    }


    Row(

        modifier = Modifier.fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween

    ){

        Text(
            text = "Question : $numOfQuiz",
            color = colorResource(id = R.color.blue_grey)
        )

        Text(
            text = quizdiffculty,
            color = colorResource(id = R.color.blue_grey)
        )
    }

    Spacer(modifier = Modifier.height(Dimens.smallspacerheight))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.verysmallviewheight)
            .clip(RoundedCornerShape(Dimens.mediumcornerradius))
            .background(colorResource(id = R.color.blue_grey)
            ),
    )


    Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))


        if (quizFetched(state)){


           val pagerState = rememberPagerState() {state.quizstate.size  }
            HorizontalPager(state = pagerState) {index ->

                QuizInterface(
                    modifier = Modifier.weight(1f),
                    onOptionSelected = {selectedIndex ->

                        event(EventQuizScreen.SetoptionSelected(index,selectedIndex))

                    },
                    quizstate = state.quizstate[index],
                    qNumber = index +1

                )
            }

            val buttonText by remember {

                derivedStateOf {
                    when(pagerState.currentPage){
                        0 ->{
                            listOf("","Next")
                        }
                        state.quizstate.size - 1 ->{
                            listOf("Previous", "Submit")
                        }
                        else ->{
                            listOf("Previous", "Next")
                        }
                    }
                }

            }


            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MediumPadding)
                    .navigationBarsPadding(),

                ){
                    val scope = rememberCoroutineScope()

                if(buttonText[0].isNotEmpty()){
                    ButtonBox(
                        text = "Previous",
                        padding = Dimens.SmallPadding,
                        fraction = 0.43f,
                        fontSize = Dimens.SmallTextSize
                    ) {

                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage -1)
                        }

                    }
                }
                else{
                    ButtonBox(
                        text = "",
                        fraction = 0.43f,
                        borderColor = colorResource(id = R.color.mid_night_blue),
                        fontSize = Dimens.SmallTextSize,
                        containerColor = colorResource(id = R.color.mid_night_blue)
                    ) {


                    }

                }



                ButtonBox(
                    text = buttonText[1],
                    padding = Dimens.SmallPadding,
                    borderColor =   colorResource(id = R.color.orange),
                    containerColor = if(pagerState.currentPage == state.quizstate.size -1) colorResource(id = R.color.orange) else colorResource(id = R.color.dark_slate_blue),
                    fraction = 1f,
                    textColor = colorResource(id = R.color.white),
                    fontSize = Dimens.SmallTextSize
                ) {


                    if(pagerState.currentPage == state.quizstate.size - 1){

                        val allanswered = state.quizstate.all { it.selectedoption != -1 }

                        if(allanswered) {

                            navController.navigate(
                                Routes.ScoreScreen.passNumOfQuestionAndCorrectAns(
                                    state.quizstate.size,
                                    state.score
                                )
                            )
                        }else {


                            Toast.makeText(context, "Please answer every question first!", Toast.LENGTH_SHORT).show()
                        }

                    }
                    else{
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }


                }
            }
        }



}}

@Composable
fun quizFetched(state: statequizScreen): Boolean {
    return when{
        state.isLoading ->{
            QuizShimmerScreen()
            false
        }

        state.quizstate.isNotEmpty() ->{
            true
        }

        else -> {
            Text(text = state.error.toString(), color = colorResource(id = R.color.white))
            false
        }
    }
}
