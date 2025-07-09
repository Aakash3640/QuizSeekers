package local.ak.quizseeekers.presentation.home.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import local.ak.quizseeekers.R
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import local.ak.quizseeekers.presentation.util.Dimens


@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val context = LocalContext.current
    Row(


        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(bottomStart = 100.dp, bottomEnd = 100.dp)
            )
            .height(150.dp)
            .background(Color(0xFF333333)) // Dark background
            .padding(horizontal = 35.dp, vertical = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {



        // Settings Icon
        IconButton(
            onClick = { Toast.makeText(context,"Soon",Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .background(Color.Gray, shape = CircleShape)
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color.Black
            )
        }

        // Title
        Box(
            modifier = Modifier
                .width(180.dp)
                .background(color = colorResource(id = R.color.blue_grey), shape = RoundedCornerShape(100))
                .padding(horizontal = 24.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Quiz Seekers",
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = Dimens.SmallTextSize
                ),

            )
        }


        // Profile Icon
        IconButton(

            onClick = { Toast.makeText(context,"Soon",Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .background(Color.Gray,shape = CircleShape)
                .size(48.dp)
        ) {


            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile",
                tint = Color.Black

            )
        }





    }
}

@Preview(showBackground = true)
@Composable
fun PreHomeHeader() {
    MaterialTheme {
        HomeHeader()
    }
}
