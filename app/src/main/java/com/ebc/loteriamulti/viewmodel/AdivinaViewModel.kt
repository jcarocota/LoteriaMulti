package com.ebc.loteriamulti.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebc.loteriamulti.network.ApiClient
import com.ebc.loteriamulti.network.JuegoService
import kotlinx.coroutines.launch

class AdivinaViewModel: ViewModel() {
    private val adivinaService = ApiClient.retrofit.create(JuegoService::class.java)

    val isLoading = mutableStateOf(false)
    val inputNumber = mutableStateOf("")
    val resultado = mutableStateOf<String?>(null)
    val errorMessage = mutableStateOf<String?>(null)

    fun verificar() {
        val numero = inputNumber.value.toIntOrNull()
        if(numero == null) {
            errorMessage.value = "Ingresa un valor correcto (0-10)"
            return
        }

        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null

            try {
                val resNum = adivinaService.verificarIntento(numero)
                resultado.value = resNum
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Falló algo y no sé que es"
            } finally {
                isLoading.value = false
            }
        }
    }

}