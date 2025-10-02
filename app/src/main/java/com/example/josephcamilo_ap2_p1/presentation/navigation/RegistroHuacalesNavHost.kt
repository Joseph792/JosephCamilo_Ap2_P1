package com.example.josephcamilo_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.josephcamilo_ap2_p1.presentation.entrada.EntradaListScreen
import com.example.josephcamilo_ap2_p1.presentation.entrada.EntradaScreen

@Composable
fun RegistroHuacalesNavHost(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.EntradaList
    ) {
        //pantalla lista de huacales
        composable<Screen.EntradaList>{

            EntradaListScreen(
                goToEntrada = { id ->
                    navHostController.navigate(Screen.Entrada(id ?: 0))
                },
                createEntrada = {
                    navHostController.navigate((Screen.Entrada(0)))
                }
            )
        }

        //pantalla formulario de huacales
        composable <Screen.Entrada>{ backStack ->
            val entradaId = backStack.toRoute<Screen.Entrada>().entradaId
            EntradaScreen(
                entradaId = entradaId,
                goBack = { navHostController.popBackStack() }
            )
        }

    }
}