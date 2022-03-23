package com.example.mispeliculas.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.StringBuilder

data class Pelicula(@SerializedName("title") val titulo: String,
                    @SerializedName("poster_path") val posterPath: String?,
                    @SerializedName("vote_average") val puntuacion: Double,
                    @SerializedName("release_date") var fecha: String,
                    @SerializedName("popularity")val popularidad: Double,
                    @SerializedName("overview") val descripcion: String):
    Serializable {

    var imagenUrl: String = ""
    var generos: String = ""
}
