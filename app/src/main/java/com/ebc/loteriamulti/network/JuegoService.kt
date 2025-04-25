package com.ebc.loteriamulti.network

import retrofit2.http.GET
import retrofit2.http.Query

interface JuegoService {

    @GET("loteria")
    suspend fun generarNumeros() : List<Int>

    @GET("adivina")
    suspend fun verificarIntento(@Query("intento") intento: Int): String
}