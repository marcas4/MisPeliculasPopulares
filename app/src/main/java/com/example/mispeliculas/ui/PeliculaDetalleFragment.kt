package com.example.mispeliculas.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mispeliculas.R
import com.example.mispeliculas.data.Pelicula


class PeliculaDetalleFragment : Fragment() {
    private var pelicula: Pelicula? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM)) {
                pelicula = it.getSerializable(ARG_ITEM) as Pelicula?
                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = pelicula?.titulo
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.pelicula_detalle, container, false)

        pelicula?.let {
            rootView.findViewById<TextView>(R.id.item_detail).text = it.descripcion
            rootView.findViewById<TextView>(R.id.generos_textview).text = "Votación: " + it.puntuacion.toString() + " - fecha: " + it.fecha + " - Popularidad: " + it.popularidad

            val poster = rootView.findViewById<ImageView>(R.id.poster_image)
            Glide
                .with(this)
                .load(it.imagenUrl).into(poster)
        }

        return rootView
    }

    companion object {

        const val ARG_ITEM = "item"
    }
}