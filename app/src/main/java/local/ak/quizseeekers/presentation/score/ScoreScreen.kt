package local.ak.quizseeekers.presentation.score


import android.annotation.SuppressLint
import android.icu.text.DecimalFormat
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import local.ak.quizseeekers.R
import local.ak.quizseeekers.presentation.util.Dimens
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import local.ak.quizseeekers.presentation.nav_graph.Routes
import kotlin.math.roundToInt


@Composable
fun ScoreScreen(
    numofQuestion : Int,
    numofCorrectAns : Int,
    navController: NavController
){

    BackHandler {
        goToHome(navController)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center
    ) {
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End

        )

        {
            IconButton(onClick = {
                goToHome(navController = navController)
            },
                ) {

                Icon(
                    painterResource(id = R.drawable.baseline_close_24),
                    contentDescription = "close",
                    tint = colorResource(id = R.color.blue_grey)
                )
            }

        }


        Spacer(modifier = Modifier.height(Dimens.smallspacerheight))

        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(Dimens.mediumcornerradius))
                .background(colorResource(id = R.color.dark_slate_blue)),
            contentAlignment = Alignment.Center

        ) {

            Column (
                    modifier = Modifier.padding(
                        horizontal = Dimens.MediumPadding,
                        vertical = Dimens.MediumPadding
                    ),

                horizontalAlignment = Alignment.CenterHorizontally

            ){

                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.congrat))
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.black))){
                        append("You attempted ")
                    }
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.green))){
                        append("$numofQuestion questions")
                    }
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.black))){
                        append(" and from that ")
                    }
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.green))){
                        append("$numofCorrectAns answers")
                    }
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.black))){
                        append(" are correct")
                    }


                }

                val scorePercentage = calculatePercentage(numofCorrectAns,numofQuestion)

                LottieAnimation(
                    modifier = Modifier.size(200.dp),
                    composition = composition,
                    iterations = 100,
                )


                Spacer(modifier = Modifier.height(Dimens.SmallSpacerheight))


                Text(
                    text = "$scorePercentage% Score",
                    color = colorResource(id = R.color.green),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.LargeTextSize,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
                Text(
                    text = "Congrats!",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.LargeTextSize,
                    fontWeight = FontWeight.Bold
                )


                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))


                Text(
                    text = "Quiz completed Successfully.",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.MediumTextSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))


                Text(
                    text = annotatedString,
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.SmallTextSize,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(Dimens.largespacerheight))


            }
        }
    }
}

fun goToHome(navController: NavController){
    navController.navigate(Routes.HomeScreen.route){
        popUpTo(Routes.HomeScreen.route){inclusive = true}
    }
}


fun calculatePercentage(k: Int, n: Int): Double {
    require(k >= 0 && n > 0) { "Invalid input: k must be non-negative and n must be positive" }

    val percentage = (k.toDouble() / n) * 100
    return (percentage * 100).roundToInt() / 100.0  // Rounded to 2 decimal places
}

