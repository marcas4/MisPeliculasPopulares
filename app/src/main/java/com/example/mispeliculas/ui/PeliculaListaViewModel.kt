package com.example.mispeliculas.ui

import androidx.lifecycle.ViewModel
import com.example.mispeliculas.data.Pelicula
import com.example.mispeliculas.repository.PeliculasRepository

class PeliculaListaViewModel: ViewModel() {
    val peliculas = PeliculasRepository.obtenerPeliculasActuales()
}