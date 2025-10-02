package com.example.josephcamilo_ap2_p1.presentation.entrada

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.josephcamilo_ap2_p1.data.local.entities.EntradaEntity
import com.example.josephcamilo_ap2_p1.data.repository.EntradasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntradasViewModel @Inject constructor(
    private val entradasRepository: EntradasRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(EntradaUiState())

    val uiState = _uiState.asStateFlow()


    fun onEvent(event: EntradaEvent){
        when (event) {
            EntradaEvent.Delete -> delete()
            EntradaEvent.New -> nuevo()
            is EntradaEvent.EntradaChange -> onEntradaChange(event.entradaId)
            is EntradaEvent.FechaChange -> onFechaChange(event.fecha)
            is EntradaEvent.NombreCienteChange -> onNombreCienteChange(event.nombreCiente)
            EntradaEvent.Save -> save()
            is EntradaEvent.CantidadChange -> onCantidadChange(event.cantidad)
            is EntradaEvent.PrecioChange -> onPrecioChange(event.precio)
        }
    }


    init {
        getEntrada()
    }

    private fun save() {
        viewModelScope.launch {
            if (_uiState.value.fecha.isNullOrBlank() || _uiState.value.nombreCiente.isNullOrBlank() ||
                _uiState.value.cantidad.toString().isNullOrBlank() || _uiState.value.precio.toString().isNullOrBlank()){
                _uiState.update {
                    it.copy(errorMessage = "Campo vacios")
                }
            }
            else{
                entradasRepository.save(_uiState.value.toEntity())
            }
        }
    }

    private fun nuevo(){
        _uiState.update {
            it.copy(
                entradaId = 0,
                fecha = "",
                nombreCiente = "",
                cantidad = 0,
                precio = 0
            )
        }
    }

    fun selectedEntrada(entradaId: Int){
        viewModelScope.launch {
            if(entradaId > 0){
                val entrada = entradasRepository.find(entradaId)
                _uiState.update {
                    it.copy(
                        entradaId = entrada?.entradaId,
                        fecha = entrada?.fecha ?: String(),
                        nombreCiente = entrada?.nombreCiente ?: String(),
                        cantidad = entrada?.cantidad,
                        precio = entrada?.precio
                    )
                }
            }
        }
    }

    private fun delete() {
        viewModelScope.launch {
            entradasRepository.delete(_uiState.value.toEntity())
        }
    }

    private fun getEntrada() {
        viewModelScope.launch {
            entradasRepository.getAll().collect { entradas ->
                _uiState.update {
                    it.copy(entradas = entradas)
                }
            }
        }
    }

    private fun onEntradaChange(entradaId: Int) {
        _uiState.update {
            it.copy(entradaId = entradaId)
        }
    }
    private fun onFechaChange(fecha: String) {
        _uiState.update {
            it.copy(fecha = fecha)
        }
    }
    private fun onNombreCienteChange(nombreCliente: String) {
        _uiState.update {
            it.copy(nombreCiente = nombreCliente)
        }
    }
    private fun onCantidadChange(cantidad: Int) {
        _uiState.update {
            it.copy(cantidad = cantidad)
        }
    }
    private fun onPrecioChange(precio: Int) {
        _uiState.update {
            it.copy(precio = precio)
        }
    }

}

fun EntradaUiState.toEntity() = EntradaEntity(
    entradaId = entradaId,
    fecha = fecha,
    nombreCiente = nombreCiente,
    cantidad = cantidad,
    precio = precio
)