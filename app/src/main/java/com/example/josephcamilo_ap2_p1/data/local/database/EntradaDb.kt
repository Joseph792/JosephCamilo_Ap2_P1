package com.example.josephcamilo_ap2_p1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.josephcamilo_ap2_p1.data.local.dao.EntradaDao
import com.example.josephcamilo_ap2_p1.data.local.entities.EntradaEntity

@Database(
    entities = [
        EntradaEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class EntradaDb : RoomDatabase() {
    abstract fun entradaDao(): EntradaDao
}