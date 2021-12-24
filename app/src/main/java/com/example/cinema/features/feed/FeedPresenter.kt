package com.example.cinema.features.feed

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.cinema.App
import com.example.cinema.core.models.Film
import com.example.cinema.features.feed.api.FeedApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@InjectViewState
class FeedPresenter : MvpPresenter<FeedView>(), CoroutineScope {
    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()

    private var films: MutableList<Film>? = mutableListOf()
    private val sortedFilms: MutableList<Film> = mutableListOf()
    var genres: MutableSet<String> = mutableSetOf()

    @Inject
    lateinit var retrofit: Retrofit

    private val api: FeedApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(FeedApi::class.java)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getFilms()
    }

    private fun getFilms() {
        if (films.isNullOrEmpty()) {
            refreshFilms()
        } else {
            viewState.onFilmsLoaded(films)
        }
    }

    fun refreshFilms() = this.launch {
        try {
            viewState.onFilmsLoading()
            val payload = withContext(Dispatchers.IO) {
                api.loadFilmsListAsync()
            }.await()
            films = payload.films.also {
                withContext(Dispatchers.Default) {
                    for (film in it) { genres.addAll(film.genres) }
                    it.sortBy { it.localizedName }
                }
            }
            viewState.onFilmsLoaded(films)
        } catch (e: IOException) {
            viewState.showMessage("Ошибка при загрузке ленты фильмов")
        }
    }

    fun sortFilmsByGenre(genre: String) = this.launch {
        viewState.onFilmsLoading()
        films?.let {
            if (genre.isEmpty()) {
                sortedFilms.clear()
                sortedFilms.addAll(it)
            } else {
                val result = withContext(Dispatchers.Default) {
                    it.filter { film -> film.genres.contains(genre) }
                }
                sortedFilms.clear()
                sortedFilms.addAll(result)
            }
        }
        viewState.onFilmsLoaded(sortedFilms)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext[Job]!!.cancel()
    }
}