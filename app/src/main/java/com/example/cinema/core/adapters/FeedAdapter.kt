package com.example.cinema.core.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.core.models.Film


class FeedAdapter(
    context: Context,
    var checkedGenre: String? = null,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val films = ArrayList<Film>()
    private val genres = ArrayList<String>()

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount() = (films.size + 1)

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 1 else 2
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewItem: View
        return if (viewType == 1) {
            viewItem = inflater.inflate(R.layout.film_list_genres, parent, false)
            GenresHolder(viewItem, listener::onGenreCheckChange)
        } else {
            viewItem = inflater.inflate(R.layout.film_list_item, parent, false)
            FilmHolder(viewItem, listener::onFilmClick)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            (holder as GenresHolder).rebindGenres(checkedGenre, genres)
        } else {
            (holder as FilmHolder).bind(films[position - 1])
        }
    }

    fun refreshFilms(list: List<Film>) {
        films.clear()
        films.addAll(list)
        notifyDataSetChanged()
    }

    fun refreshGenres(list: List<String>) {
        genres.clear()
        genres.addAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onFilmClick(film: Film)
        fun onGenreCheckChange(genre: String)
    }
}