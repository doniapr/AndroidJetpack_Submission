package com.doniapr.moviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.doniapr.moviecatalogue.BuildConfig
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.data.source.local.Movie
import com.doniapr.moviecatalogue.ui.detail.DetailMovieActivity
import kotlinx.android.synthetic.main.item_layout.view.*

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var movies: List<Movie> = ArrayList()

    fun setData(movies: List<Movie>) {
        this.movies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MovieViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                this.setOnClickListener {
                    val intent = Intent(context, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.DETAIL_ID, movie)
                    }
                    itemView.context?.startActivity(intent)
                }
                val textTitle = "${movie.title} (${movie.releaseDate?.slice(0..3)})"
                txt_title.text = textTitle
                Glide.with(this.context).load(BuildConfig.BASE_URL_IMAGE + movie.posterPath)
                    .placeholder(R.drawable.poster_placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(img_poster)
            }
        }
    }
}