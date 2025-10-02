package com.example.josephcamilo_ap2_p1.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object EntradaList : Screen()
    @Serializable
    data class Entrada(val entradaId: Int?) : Screen()
}