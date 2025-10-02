package com.example.josephcamilo_ap2_p1.presentation.entrada

import com.example.josephcamilo_ap2_p1.data.local.entities.EntradaEntity

data class EntradaUiState(
    val entradaId: Int? = null,
    val fecha: String = "",
    val nombreCiente: String = "",
    val cantidad: Int? = 0,
    val precio: Int? = 0,
    val errorMessage: String? = null,
    val entradas: List<EntradaEntity> = emptyList()
)