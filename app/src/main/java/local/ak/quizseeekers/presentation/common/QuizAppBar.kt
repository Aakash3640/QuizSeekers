package local.ak.quizseeekers.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import local.ak.quizseeekers.R




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizAppBar(

    quizcategory: String,
    onBackClick: () -> Unit
){


    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xFF333333),
            actionIconContentColor = colorResource(id = R.color.black),
            navigationIconContentColor = colorResource(id = R.color.black),
        ),
        title = {
            Text(

                text = quizcategory,
                color = colorResource(id = R.color.blue_grey),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )},


        navigationIcon = {
            IconButton(onClick = onBackClick) {


                Icon(painterResource(id = R.drawable.baseline_arrow_circle_left_24), contentDescription = "")
            }
        }




    )

}