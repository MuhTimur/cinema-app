package com.example.cinema.features.feed

import com.arellomobile.mvp.MvpView
import com.example.cinema.core.models.Film

interface FeedView : MvpView {
    fun showMessage(msg: String)
    fun onFilmsLoading()
    fun onFilmsLoaded(films: List<Film>?)
}