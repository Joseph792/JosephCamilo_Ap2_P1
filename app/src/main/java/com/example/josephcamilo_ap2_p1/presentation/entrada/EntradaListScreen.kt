package com.example.josephcamilo_ap2_p1.presentation.entrada

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.josephcamilo_ap2_p1.data.local.entities.EntradaEntity

@Composable
fun EntradaListScreen(
    viewModel: EntradasViewModel = hiltViewModel(),
    goToEntrada: (Int) -> Unit,
    createEntrada: () -> Unit,
    deleteEntrada: ((EntradaEntity) -> Unit)? = null
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    EntradaListBodyScreen(
        uiState = uiState,
        goToEntrada = goToEntrada,
        createEntrada = createEntrada,
        deleteEntrada = { entrada ->
            viewModel.onEvent(EntradaEvent.EntradaChange(entrada.entradaId ?: 0))
            viewModel.onEvent(EntradaEvent.Delete)
        }
    )
}

@Composable
private fun EntradaRow(
    it: EntradaEntity,
    goToEntrada: () -> Unit,
    deleteEntrada:(EntradaEntity) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(modifier = Modifier.weight(1f), text = it.entradaId.toString(), color = Color.Black)
        Text(modifier = Modifier.weight(1f), text = it.fecha.toString(), color = Color.Black)
        Text(
            modifier = Modifier.weight(2f),
            text = it.nombreCiente,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        Text(modifier = Modifier.weight(2f), text = it.cantidad.toString(), color = Color.Black)
        Text(modifier = Modifier.weight(2f), text = it.precio.toString(), color = Color.Black)
        IconButton(onClick = goToEntrada) {
            Icon(Icons.Default.Edit, contentDescription = "Editar", tint = MaterialTheme.colorScheme.primary)
        }
        IconButton(onClick = {deleteEntrada(it)}) {
            Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
        }

    }
    HorizontalDivider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntradaListBodyScreen(
    uiState: EntradaUiState,
    goToEntrada: (Int) -> Unit,
    createEntrada: () -> Unit,
    deleteEntrada: (EntradaEntity) -> Unit
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Lista de Huacales") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = createEntrada
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create a new Entrada"
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(uiState.entradas) { entrada ->
                    EntradaRow(
                        it = entrada,
                        goToEntrada = { goToEntrada(entrada.entradaId ?: 0) },
                        deleteEntrada = deleteEntrada
                    )
                }
            }
        }
    }
}

