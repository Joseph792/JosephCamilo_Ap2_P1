package com.example.josephcamilo_ap2_p1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.josephcamilo_ap2_p1.data.local.database.EntradaDb
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideEntradaDb(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            EntradaDb::class.java,
            "Entrada.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideEntradaDao(entradaDb: EntradaDb) = entradaDb.entradaDao()
}