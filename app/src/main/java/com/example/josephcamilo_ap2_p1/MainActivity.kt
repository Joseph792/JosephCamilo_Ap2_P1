package com.example.josephcamilo_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.josephcamilo_ap2_p1.data.local.database.EntradaDb
import com.example.josephcamilo_ap2_p1.presentation.navigation.RegistroHuacalesNavHost
import com.example.josephcamilo_ap2_p1.ui.theme.JosephCamilo_Ap2_P1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var entradaDb: EntradaDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        entradaDb = Room.databaseBuilder(
            applicationContext,
            EntradaDb::class.java,
            "Entrada.db"
        ).fallbackToDestructiveMigration()
            .build()

        setContent {
            JosephCamilo_Ap2_P1Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        RegistroHuacalesNavHost(
                            navHostController = rememberNavController()
                        )
                    }
                }
            }
        }
    }
}