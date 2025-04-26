package com.ebc.loteriamulti.views.navviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ebc.loteriamulti.viewmodel.AdivinaViewModel


@Preview(showBackground = true)
@Composable
fun AdivinaView(
    navController: NavHostController = rememberNavController(),
    viewModel: AdivinaViewModel = AdivinaViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.inputNumber.value,
            //Recuerda: it es la variable auxiliar default de este callback
            onValueChange = { viewModel.inputNumber.value = it },
            label = { Text("Dame un número") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )

        viewModel.errorMessage.value?.let {
            error ->
                Text(text = error, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }

        Button(
            onClick = {viewModel.verificar()},
            modifier = Modifier.padding(16.dp)
        ) {
            Text("¡Intentar!")
        }

        if (viewModel.isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier.
                padding(top = 16.dp)
            )
        }

        viewModel.resultado.value?.let {
            Text(text = it,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally))
        }
    }
}