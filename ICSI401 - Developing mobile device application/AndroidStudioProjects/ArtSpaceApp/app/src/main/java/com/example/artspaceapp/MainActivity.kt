package com.example.artspaceapp

import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var current by remember { mutableStateOf(1) }
    val image = when (current) {
        1 -> R.drawable.first
        2 -> R.drawable.second
        3 -> R.drawable.third
        4 -> R.drawable.fourth
        else -> R.drawable.first
    }
    val title = when (current) {
        1 -> R.string.title1
        2 -> R.string.title2
        3 -> R.string.title3
        4 -> R.string.title4
        else -> R.string.title1
    }
    val artist = when (current) {
        1 -> stringResource(R.string.artist1) + ' ' + stringResource(R.string.year1)
        2 -> stringResource(R.string.artist2) + ' ' + stringResource(R.string.year2)
        3 -> stringResource(R.string.artist3) + ' ' +stringResource(R.string.year3)
        4 -> stringResource(R.string.artist4) + ' ' +stringResource(R.string.year4)
        else -> stringResource(R.string.artist1) + ' ' + stringResource(R.string.year1)
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            modifier = Modifier
                .size(height = 500.dp, width = 350.dp)
                .shadow(15.dp, RectangleShape)
                .border(BorderStroke(3.dp, SolidColor(Color.White)), RectangleShape)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(30.dp, SolidColor(Color.White)), RectangleShape)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .size(height = 120.dp, width = 350.dp)
                .background(color = Color(218, 245, 243))
                .wrapContentSize(Alignment.Center),


            ) {
            Text(
                text = stringResource(title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black
            )
            Text(
                text = artist,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()

        ) {

            Button(   
                modifier= Modifier.size(width = 150.dp, height =50.dp),
                onClick = {current = when (current) {1 -> 4 else -> --current }
                }) {
                Text(text = "Previous")
            }

            Spacer(modifier = Modifier.width(50.dp))

            Button(
                modifier= Modifier.size(width = 150.dp, height =50.dp),
                onClick = {current = when (current) { in 1..3 -> ++current else -> 1}
                }) {
                Text(text = "Next")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}