package com.example.josephcamilo_ap2_p1.data.repository

import com.example.josephcamilo_ap2_p1.data.local.dao.EntradaDao
import com.example.josephcamilo_ap2_p1.data.local.entities.EntradaEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class EntradasRepository @Inject constructor(
    private val dao: EntradaDao
) {
    suspend fun save(entrada: EntradaEntity) = dao.save(entrada)

    suspend fun find(id: Int): EntradaEntity? = dao.find(id)

    suspend fun delete(entrada: EntradaEntity) = dao.delete(entrada)

    fun getAll(): Flow<List<EntradaEntity>> = dao.getAll()
}