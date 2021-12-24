package com.example.cinema.core.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.core.models.Film
import com.google.android.flexbox.FlexboxLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.film_list_item.view.*

class FilmHolder(view: View, private val listener: (Film) -> Unit) :
    RecyclerView.ViewHolder(view) {

    internal fun bind(film: Film) = with(itemView) {
        val params = filmImageView.layoutParams
        if (params is FlexboxLayoutManager.LayoutParams) {
            params.flexGrow = 1f
            params.flexShrink = 1f
        }
        if ( !film.imageUrl.isNullOrBlank() ) {
            Picasso.get()
                .load(film.imageUrl)
                .fit()
                .centerCrop()
                .into(filmImageView)
        }
        nameTextView.text = film.localizedName

        itemView.setOnClickListener { listener.invoke(film) }
    }

}