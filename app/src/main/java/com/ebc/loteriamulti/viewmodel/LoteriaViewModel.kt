package com.ebc.loteriamulti.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebc.loteriamulti.network.ApiClient
import com.ebc.loteriamulti.network.JuegoService
import kotlinx.coroutines.launch

class LoteriaViewModel: ViewModel() {
    private val loteriaService = ApiClient.retrofit.create(JuegoService::class.java)

    val isLoading = mutableStateOf(false)
    val numeros = mutableStateListOf<Int>()
    val errorMessage = mutableStateOf<String?>(null)

    fun generarNumeros() {
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null

            try {
                val result = loteriaService.generarNumeros()
                numeros.clear()
                numeros.addAll(result)
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Falló algo y no sé que es"
            } finally {
                isLoading.value = false
            }
        }
    }
}