package com.example.cinema.features.details

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinema.R
import com.example.cinema.core.models.Film
import com.example.cinema.features.BaseActivity
import com.example.cinema.features.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.view.*

class DetailsFragment : BaseFragment() {
    private var film: Film? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context) {
            is BaseActivity -> return
            else -> throw ClassCastException("$context must implement NavigationListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutId(), container, false)
        film = arguments?.getParcelable<Film>(FILM_KEY)
        film?.also {
            view.nameTextView.text = it.name
            view.yearTextView.text = "${it.year}"
            it.rating?.apply {
                val color = when {
                    this.compareTo(7f) >= 0 -> Color.GREEN
                    this.compareTo(5f) < 0 -> Color.RED
                    else -> Color.DKGRAY
                }
                view.ratingTextView.text = "$this"
                view.ratingTextView.setTextColor(color)
            }
            Picasso.get()
                .load(it.imageUrl)
                .fit()
                .centerCrop()
                .into(view.filmImageView)
            view.descriptionTextView.apply {
                text = it.description
                movementMethod = ScrollingMovementMethod()
            }
            (activity as BaseActivity).supportActionBar?.apply {
                title = it.localizedName
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }
        return view
    }

    override fun getLayoutId() = R.layout.fragment_details

    companion object {
        private const val FILM_KEY = "FILM"

        fun newInstance(film: Film): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(FILM_KEY, film)
                }
            }
        }
    }
}