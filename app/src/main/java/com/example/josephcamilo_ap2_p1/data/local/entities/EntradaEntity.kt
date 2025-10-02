package com.example.josephcamilo_ap2_p1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Entradas")
data class EntradaEntity(
    @PrimaryKey
    val entradaId: Int? = null,
    val fecha: String,
    val nombreCiente: String = "",
    val cantidad: Int? = 0,
    val precio: Int? = 0
)