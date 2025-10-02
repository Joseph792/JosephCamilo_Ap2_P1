package com.example.josephcamilo_ap2_p1.presentation.entrada

sealed interface EntradaEvent{
    data class EntradaChange(val entradaId: Int): EntradaEvent
    data class FechaChange(val fecha: String): EntradaEvent
    data class NombreCienteChange(val nombreCiente: String): EntradaEvent
    data class CantidadChange(val cantidad: Int): EntradaEvent
    data class PrecioChange(val precio: Int): EntradaEvent
    data object Save: EntradaEvent
    data object Delete: EntradaEvent
    data object New: EntradaEvent
}