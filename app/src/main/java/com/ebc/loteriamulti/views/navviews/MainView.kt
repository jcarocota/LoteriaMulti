package com.ebc.loteriamulti.views.navviews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ebc.loteriamulti.R
import com.ebc.loteriamulti.ui.theme.LoteriaMultiTheme

@Composable
fun MainView(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.main_view_tittle),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.purple_700)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            GameOption(
                idRaw = R.raw.lottie_loteria,
                label = stringResource(R.string.lottery),
                onClick = { navController.navigate("loteria")}
            )
            GameOption(
                idRaw = R.raw.lottie_adivina,
                label = stringResource(R.string.guess_number),
                onClick = { }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameOption(
    idRaw: Int = R.raw.lottie_loteria,
    label: String = "Opción",
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(idRaw)
        )
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(150.dp)
        )
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

/*@Preview(showBackground = true)
@Composable
fun GameOptionPreview() {
    LoteriaMultiTheme {
        GameOption(R.raw.lottie_loteria, "Opción", {})
    }
}*/

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    LoteriaMultiTheme {
        MainView(rememberNavController())
    }
}