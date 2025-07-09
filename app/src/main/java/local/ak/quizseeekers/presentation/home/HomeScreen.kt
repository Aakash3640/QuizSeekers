package local.ak.quizseeekers.presentation.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import local.ak.quizseeekers.presentation.common.AppDropDownMenu
import local.ak.quizseeekers.presentation.common.ButtonBox
import local.ak.quizseeekers.presentation.home.component.HomeHeader
import local.ak.quizseeekers.presentation.nav_graph.Routes
import local.ak.quizseeekers.presentation.util.Constants
import local.ak.quizseeekers.presentation.util.Dimens
import local.ak.quizseeekers.presentation.util.Dimens.MediumPadding
import local.ak.quizseeekers.presentation.util.Dimens.MediumSpacerHeight
import local.ak.quizseeekers.presentation.util.Dimens.SmallSpacerheight







@Preview
@Composable
fun PrevHome(){
    HomeScreen(state = StateHomeScreen(), event = {},
        navController = NavController(LocalContext.current),
        LocalContext.current)
}

@Composable
fun  HomeScreen(
    state : StateHomeScreen,
event : (EventHomeScreen) ->Unit,
    navController: NavController,
    context: Context = LocalContext.current

) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

    ){

        HomeHeader()

        Spacer(modifier = Modifier.height(Dimens.largespacerheight))
        AppDropDownMenu(menuName = "Number of Questions:", menuList = Constants.numberAsString, text = state.numberofQuiz.toString(), onDropDownClick = {event(EventHomeScreen.SetnumberofQuiz(it.toInt()))})

        Spacer(modifier = Modifier.height(SmallSpacerheight))
        AppDropDownMenu(menuName = "Select Category:", menuList = Constants.categories, text = state.category, onDropDownClick = {event(EventHomeScreen.SetQuizcategory(it))})


        Spacer(modifier = Modifier.height(SmallSpacerheight))
        AppDropDownMenu(menuName = "Select Difficulty:", menuList = Constants.difficulty, text = state.difficulty, onDropDownClick = {event(EventHomeScreen.SetQuizdiffulty(it))})



        Spacer(modifier = Modifier.height(SmallSpacerheight))
        AppDropDownMenu(menuName = "Select Type:", menuList = Constants.type, text = state.type, onDropDownClick = {event(EventHomeScreen.SetQuiztype(it))})




        Spacer(modifier = Modifier.height(MediumSpacerHeight))


        ButtonBox(text = "Generate Quiz", padding = MediumPadding){

            if (isInternetAvailable(context)) {
                navController.navigate(
                    route = Routes.QuizScreen.PassQuizparam(
                        state.numberofQuiz,
                        state.category,
                        state.difficulty,
                        state.type
                    )
                )
            } else {
                Toast.makeText(context, "No internet connection!", Toast.LENGTH_SHORT).show()
            }
        }







    }
}

fun isInternetAvailable(context: Context): Boolean {

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

}


