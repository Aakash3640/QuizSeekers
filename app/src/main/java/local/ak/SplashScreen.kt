package local.ak

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import local.ak.quizseeekers.R
import local.ak.quizseeekers.presentation.nav_graph.Routes
import java.lang.reflect.Modifier

@Preview(showBackground = true)
@Composable
fun PreSplashScreen() {
    SplashScreenPreviewable()
}

@Composable
fun SplashScreenPreviewable() {
    SplashScreen(navController = null)
}


@Composable
fun SplashScreen(navController: NavController? = null) {
    // Lottie animation state
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.quiz_logo))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,
        speed = 1.0f
    )

    // Navigate after animation
    LaunchedEffect(key1 = true) {
        delay(2500) // wait a bit longer than animation if needed
        navController?.navigate(Routes.HomeScreen.route) {
            popUpTo(Routes.Splashscreen.route) { inclusive = true }
            launchSingleTop = true
        }
    }

    Box(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mid_night_blue)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = androidx.compose.ui.Modifier.size(300.dp)
            )

            Spacer(modifier = androidx.compose.ui.Modifier.height(36.dp))

            Text(
                text = "Quiz Seekers",
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
