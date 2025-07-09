package local.ak.quizseeekers.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import local.ak.quizseeekers.R
import local.ak.quizseeekers.presentation.util.Dimens
import local.ak.quizseeekers.presentation.util.Dimens.MediumTextSize
import local.ak.quizseeekers.presentation.util.Dimens.SmallBorderWidth
import local.ak.quizseeekers.presentation.util.Dimens.SmallTextSize


@Preview(showBackground = true)
@Composable
fun ButtonBoxPreview() {
    MaterialTheme {
        ButtonBox(
            text = "Generate Quiz",
            onClick = {}
        )
    }
}


@Composable
fun ButtonBox(
    modifier: Modifier  = Modifier,
    text : String,
    padding : Dp = Dimens.SmallPadding,
    borderColor: Color = colorResource(id = R.color.black),
    containerColor: Color = colorResource(id = R.color.blue_grey),
    textColor : Color = colorResource(id = R.color.black),
    fontSize : TextUnit = MediumTextSize,
    fraction : Float = 1f,
    onClick :() -> Unit
){

    Box(

        modifier = Modifier
            .padding(padding)
            .border(SmallBorderWidth, borderColor, RoundedCornerShape(Dimens.LargeCornerRadius))
            .clickable { onClick() }
            .fillMaxWidth(fraction)
            .height(Dimens.MediumBoxHeight)
            .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
            .background(containerColor)
            ,
        contentAlignment = Alignment.Center

    ){
        Text(
            text = text,
            fontSize = fontSize,
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = textColor

        )

    }

}