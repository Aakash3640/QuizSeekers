package local.ak.quizseeekers.presentation.quiz.component


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder

import com.google.accompanist.placeholder.shimmer
import local.ak.quizseeekers.presentation.util.Dimens


@Preview
@Composable
fun PreviewQuizShimmerScreen() {
    QuizShimmerScreen()
}


@Composable
fun QuizShimmerScreen() {
    val baseColor = Color(0xFF2C2C2E)        // dark base
    val highlightColor = Color(0xFF3A3A3C)   // slightly lighter for shimmer

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Question bubble + text
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .placeholder(
                        visible = true,
                        color = baseColor,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = highlightColor)
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .placeholder(
                        visible = true,
                        color = baseColor,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = highlightColor)
                    )
            )
        }

        // 4 options
        repeat(4) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .placeholder(
                        visible = true,
                        color = baseColor,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = highlightColor)
                    )
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(Dimens.extralargespacerheigth))

        // Bottom buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(2) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .placeholder(
                            visible = true,
                            color = baseColor,
                            highlight = PlaceholderHighlight.shimmer(highlightColor = highlightColor)
                        )
                )
            }
        }
    }

}
