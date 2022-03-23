package com.example.mispeliculas.api

import com.example.mispeliculas.data.PeliculasActuales
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDBService {
    @GET("movie/popular")
    fun obtenerPeliculasActuales(
        @Query("api_key")apiKey: String,
        @Query("language")language: String,
        @Query("page")page: Int,
        @Query("region")region: String
    ): Call<PeliculasActuales>

}