package com.ebc.loteriamulti.views.navviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ebc.loteriamulti.R
import com.ebc.loteriamulti.viewmodel.LoteriaViewModel

@Preview(showBackground = true)
@Composable
fun LoteriaView(
    navController: NavHostController = rememberNavController(),
    viewModel: LoteriaViewModel = LoteriaViewModel()
) {
    val numerosPrueba: MutableList<Int> = mutableListOf(9, 50, 21, 7, 42, 1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        if(viewModel.isLoading.value) {
            CircularProgressIndicator()
        } else {
            Button(onClick = {viewModel.generarNumeros() }) {
                Text(text = stringResource(R.string.generate_numbers))
            }

            viewModel.errorMessage.value?.let { error ->
                Text(text = error, color = Color.Red)
            }

            if(viewModel.numeros.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    itemsIndexed(viewModel.numeros) { index, item ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(50.dp)
                                .background(color = Color.Red, shape = CircleShape)
                        ) {
                            Text(
                                text = item.toString(),
                                color = Color.White,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
