package com.example.cinema.features

import com.example.cinema.core.models.Film

interface NavigationListener {
    fun onFilmDetailsNavigate(film: Film)
}