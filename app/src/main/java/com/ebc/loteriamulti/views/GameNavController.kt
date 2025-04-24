package com.ebc.loteriamulti.views

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ebc.loteriamulti.views.navviews.LoteriaView
import com.ebc.loteriamulti.views.navviews.MainView

@Composable
fun GameNavController() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainView(navController)
        }
        composable("loteria") {
            LoteriaView(navController)
        }
    }
}