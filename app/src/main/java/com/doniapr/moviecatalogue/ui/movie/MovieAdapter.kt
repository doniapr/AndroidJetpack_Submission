package com.doniapr.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.data.Movie
import kotlinx.android.synthetic.main.item_layout.view.*

class MovieAdapter(
    private val movies: List<Movie>,
    private val listener: (Movie) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MovieViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, listener)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, listener: (Movie) -> Unit) {
            with(itemView) {
                this.setOnClickListener { listener(movie) }
                val textTitle = "${movie.title} (${movie.year})"
                txt_title.text = textTitle
                Glide.with(this.context).load(movie.poster).into(img_poster)
            }
        }
    }
}