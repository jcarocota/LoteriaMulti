package com.ebc.loteriamulti.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ebc.loteriamulti.viewmodel.AdivinaViewModel
import com.ebc.loteriamulti.viewmodel.LoteriaViewModel
import com.ebc.loteriamulti.views.navviews.AdivinaView
import com.ebc.loteriamulti.views.navviews.LoteriaView
import com.ebc.loteriamulti.views.navviews.MainView
import kotlinx.coroutines.launch

@Composable
fun GameNavController() {
    val navController = rememberNavController()

    val loteriaViewModel: LoteriaViewModel = LoteriaViewModel()
    val adivinaViewModel: AdivinaViewModel = AdivinaViewModel()

    //Remembers del top var y otros relacionados
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        scaffoldState = scaffoldState,

        drawerContent = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
            ) {
                TextButton(
                   onClick = {
                       navController.navigate("loteria")
                       scope.launch { scaffoldState.drawerState.close()
                       }
                   }
                ) { Text("Lotería") }
                TextButton(
                    onClick = {
                        navController.navigate("adivina")
                        scope.launch { scaffoldState.drawerState.close()
                        }
                    }
                ) { Text("Adivina el número") }
            }
        },

        topBar = {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route

            val appBarTitle = when (currentRoute) {
                "loteria" -> "Lotería clásica"
                "adivina" -> "Adivina el número"
                else -> "Loterías y Más"
            }

            TopAppBar(
                title = {
                    Text(
                        text = appBarTitle,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch { scaffoldState.drawerState.open() }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menú"
                        )
                    }
                }
            )
        }
    ) {
        innerPading ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(innerPading)) {
            composable("main") {
                MainView(navController)
            }
            composable("loteria") {
                LoteriaView(navController, loteriaViewModel)
            }
            composable("adivina") {
                AdivinaView(navController, adivinaViewModel)
            }
        }
    }


}