package com.example.lemonade
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

/*
1. LemonadeApp() undsen nuurend bairlaluudig ni todorhoilno.
    1.1 header
    1.2 button bairlah surface
2. LemonadeAppController() func zarlana ene ni undsen content-n UI-g gargana. parametruudere damjuulad haruulah yma gargana
3.
*/
@Preview
@Composable
fun LemonadeApp(){
    var Step by remember { mutableStateOf(1) }
    var Tap by remember { mutableStateOf(0) }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(color = 0xFFd2e8d5)))
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.Yellow)
                .wrapContentSize(Alignment.Center)){
            Text(text = stringResource(R.string.app_name),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp)
        }
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            when(Step) {
                1 -> {
                    LemonadeAppController(
                        lemonImage = R.drawable.lemon_tree,
                        lemonText = R.string.lemon_select,
                        LemonDescription = R.string.lemon_tree_description,
                        ImageClick = {
                            Step = 2
                            Tap = (2..4).random()
                        }
                    )
                }
                2 -> {
                    LemonadeAppController(
                        lemonImage = R.drawable.lemon_squeeze,
                        lemonText = R.string.lemon_squeeze,
                        LemonDescription = R.string.lemon_squeeze_description,
                        ImageClick = {
                            Tap--
                            if(Tap == 0)
                                Step = 3
                        }
                    )
                }
                3 -> {
                    LemonadeAppController(
                        lemonImage = R.drawable.lemon_drink,
                        lemonText = R.string.lemon_drink,
                        LemonDescription = R.string.lemon_drink_description,
                        ImageClick = {
                            Step = 4
                        }
                    )
                }
                4 -> {
                    LemonadeAppController(
                        lemonImage = R.drawable.lemon_restart,
                        lemonText = R.string.lemon_restart,
                        LemonDescription = R.string.lemon_restart_description,
                        ImageClick = {
                            Step = 1

                        }
                    )
                }
            }
        }
    }
}
@Composable
fun LemonadeAppController(
  lemonImage: Int,
  lemonText: Int,
  LemonDescription: Int,
  ImageClick: () -> Unit,
  modifier: Modifier = Modifier
) {
    Box(modifier = modifier){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = ImageClick,
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray
                )
            ) {
                Image(painter = painterResource(lemonImage), contentDescription = stringResource(LemonDescription))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(lemonText),
                fontSize = 18.sp)
        }
    }
}
