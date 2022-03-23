package com.example.mispeliculas.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mispeliculas.api.RetrofitServiceBuilder
import com.example.mispeliculas.api.TheMovieDBService
import com.example.mispeliculas.data.Pelicula
import com.example.mispeliculas.data.PeliculasActuales
import retrofit2.Call
import retrofit2.Response

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "ef02e0bfd75b7fbc57e8fc934e769218"
private const val IMAGE_URL_ROOT = "https://image.tmdb.org/t/p/w500/"

object PeliculasRepository {
    private val peliculasService: TheMovieDBService =
        RetrofitServiceBuilder(BASE_URL)
            .buildService(TheMovieDBService::class.java)
    private val peliculasCompletaLiveData = MutableLiveData<List<Pelicula>>()

    fun obtenerPeliculasActuales(): LiveData<List<Pelicula>> {
        val call = peliculasService.obtenerPeliculasActuales(API_KEY, "em", 1, "US")
        call.enqueue(object: retrofit2.Callback<PeliculasActuales> {
            override fun onResponse(
                call: Call<PeliculasActuales>,
                response: Response<PeliculasActuales>
            ) {
                if (response.isSuccessful) {
                    val peliculas = response.body()?.peliculas ?: listOf()
                    completarInformacion(peliculas)

                }
            }

            override fun onFailure(call: Call<PeliculasActuales>, t: Throwable) {

            }

        })
        return peliculasCompletaLiveData
    }

    private fun completarInformacion(peliculas: List<Pelicula>) {
        val call = peliculasService.obtenerPeliculasActuales(API_KEY, "en", 1, "US")
        call.enqueue(object: retrofit2.Callback<PeliculasActuales> {
            override fun onResponse(
                call: Call<PeliculasActuales>,
                response: Response<PeliculasActuales>
            ) {
                if (response.isSuccessful){
                    peliculas.forEach { pelicula ->
                        pelicula.imagenUrl =  IMAGE_URL_ROOT + pelicula.posterPath
                    }
                    peliculasCompletaLiveData.value = peliculas
                }
            }

            override fun onFailure(call: Call<PeliculasActuales>, t: Throwable) {

            }

            })
    }
}