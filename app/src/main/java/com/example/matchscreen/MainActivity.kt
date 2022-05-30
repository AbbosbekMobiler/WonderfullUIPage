package com.example.matchscreen

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matchscreen.ui.theme.MatchScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MatchScreenTheme(darkTheme = false) {
                MatchScreenDatingApp()
            }
        }
    }
}

@Composable
fun MatchScreenDatingApp(
    backgroundColors : List<androidx.compose.ui.graphics.Color> = listOf(Color(0xFFF518A0),Color(0xFFEE9539))
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = Brush.verticalGradient(backgroundColors))
        .padding(vertical = 64.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            MatchScreenTopText()
        }
        MatchScreenImageBox()
        Column {
            MatchScreenButtons()
        }
    }
}

@Composable
fun MatchScreenTopText() {
    Text(
        text = "It's a Match!",
        style = TextStyle(color = White, fontWeight = FontWeight.Bold, fontSize = 32.sp)
    )

    Text(
        text = "You and Grace like each other",
        style = TextStyle(color = White, fontWeight = FontWeight.Normal, fontSize = 20.sp)
    )
}

@Composable
fun MatchScreenImageBox() {
    Box(contentAlignment = Alignment.Center){
        Row {
            MatchScreenImageStyle(imageId = R.drawable.evans)
            MatchScreenImageStyle(imageId = R.drawable.scarlett)
        }
        Icon(modifier = Modifier
            .background(
                color = Red,
                shape = CircleShape)
            .border(width = 3.dp, color = White, shape = CircleShape)
            .padding(12.dp),
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Match",
            tint = White
        )
    }
}

@Composable
fun MatchScreenImageStyle(
    imageId : Int
) {
    Image(modifier = Modifier
        .size(120.dp)
        .clip(CircleShape)
        .border(
            width = 3.dp,
            color = White,
            shape = CircleShape),
        painter = painterResource(id = imageId),
        contentDescription = "Image",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun MatchScreenButtons(context: Context = LocalContext.current.applicationContext) {
    MatchScreenButtonStyle(
        buttonText = "Send Her Message",
        backgroundColorAlpha = 1f,
        textColor = Color(0xFFF518A0)
    ){
        Toast.makeText(context,"Send her Message",Toast.LENGTH_SHORT).show()
    }
    Spacer(modifier = Modifier.height(12.dp))

    MatchScreenButtonStyle(buttonText = "Keep Swiping",
        backgroundColorAlpha = 0.2f,
        textColor = White) {
        Toast.makeText(context,"Keep Swiping",Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun MatchScreenButtonStyle(
    buttonText : String,
    backgroundColorAlpha : Float,
    textColor : androidx.compose.ui.graphics.Color,
    buttonClick : ()-> Unit
) {
    Box(modifier = Modifier
        .padding(horizontal = 36.dp)
        .fillMaxWidth()
        .background(color = White.copy(alpha = backgroundColorAlpha),
            shape = RoundedCornerShape(20.dp))
        .clickable(indication = null, interactionSource = MutableInteractionSource()) {
            buttonClick
        },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = buttonText,
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}