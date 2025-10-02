package com.example.josephcamilo_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.josephcamilo_ap2_p1.data.local.entities.EntradaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntradaDao {
    @Upsert()
    suspend fun save(entrada: EntradaEntity)

    @Query(
        """
        SELECT * 
        FROM Entradas 
        WHERE entradaId=:id  
        LIMIT 1
        """)
    suspend fun find(id: Int): EntradaEntity?

    @Delete
    suspend fun delete(entrada: EntradaEntity)

    @Query("SELECT * FROM Entradas")
    fun getAll(): Flow<List<EntradaEntity>>
}