package local.ak.quizseeekers.presentation.quiz.component

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import local.ak.quizseeekers.R
import local.ak.quizseeekers.presentation.nav_graph.Routes
import local.ak.quizseeekers.presentation.quiz.QuizState
import local.ak.quizseeekers.presentation.util.Dimens


//@Preview
//@Composable
//fun Preview(){
//QuizInterface(
//    onOptionSelected = {},
//    1,
//    quizstate = QuizState(),
//
//
//)
//
//}


@Composable
fun QuizInterface(

    onOptionSelected: (Int) -> Unit,
    qNumber : Int,
    quizstate : QuizState,
    modifier: Modifier

) {
    val question = quizstate.quiz?.question!!
        .replace("&quot;","\"")
        .replace("&#039;", "\'")
        .replace("&amp;", "&")
        .replace("&lt;", "<")
        .replace("&gt;", ">")

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    )
    {

        Column(
            modifier = Modifier.wrapContentHeight()
        ){

            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(12.dp)
            ){

                Text (
                    modifier = Modifier.weight(1f),
                    text = "$qNumber.",
                    color = colorResource(id = R.color.blue_grey),
                    fontSize = Dimens.SmallTextSize
                )

                Text(

                    modifier = Modifier.weight(9f),
                    text = question,
                    color = colorResource(id = R.color.blue_grey),
                    fontSize = Dimens.SmallTextSize
                )

            }

            Spacer(modifier = Modifier.height(Dimens.mediumspacerheight))

            Column (modifier = Modifier.padding(horizontal = 10.dp)){

                val options = if (quizstate.quiz.type == "boolean") {
                    listOf(
                        "A" to quizstate.shuffledoption[0].replace("&quot;","\"")
                            .replace("&#039;", "\'")
                            .replace("&amp;", "&")
                            .replace("&lt;", "<")
                            .replace("&gt;", ">"),
                        "B" to quizstate.shuffledoption[1].replace("&quot;","\"")
                            .replace("&#039;", "\'")
                            .replace("&amp;", "&")
                            .replace("&lt;", "<")
                            .replace("&gt;", ">")
                    )
                } else {
                    listOf(
                        "A" to quizstate.shuffledoption[0].replace("&quot;","\"")
                            .replace("&#039;", "\'")
                            .replace("&amp;", "&")
                            .replace("&lt;", "<")
                            .replace("&gt;", ">"),
                        "B" to quizstate.shuffledoption[1].replace("&quot;","\"")
                            .replace("&#039;", "\'")
                            .replace("&amp;", "&")
                            .replace("&lt;", "<")
                            .replace("&gt;", ">"),
                        "C" to quizstate.shuffledoption[2].replace("&quot;","\"")
                            .replace("&#039;", "\'")
                            .replace("&amp;", "&")
                            .replace("&lt;", "<")
                            .replace("&gt;", ">"),
                        "D" to quizstate.shuffledoption[3].replace("&quot;","\"")
                            .replace("&#039;", "\'")
                            .replace("&amp;", "&")
                            .replace("&lt;", "<")
                            .replace("&gt;", ">")
                    )
                }



                Column {


                    options.forEachIndexed { index, (optionNumber, optionText) ->

                        if(optionText.isNotEmpty()){

                            quizOption(
                                optionNumber = optionNumber,
                                option = optionText,
                                onOptionclick = {onOptionSelected(index)},
                                selected = quizstate.selectedoption == index,
                                onUnselectedOtion = {onOptionSelected(-1)}
                            )
                        }

                        Spacer(modifier = Modifier.height(Dimens.smallspacerheight))
                    }
                }
                Spacer(modifier = Modifier.height(Dimens.largespacerheight))


            }

        }

    }


}