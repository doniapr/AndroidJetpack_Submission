package com.doniapr.moviecatalogue.utils

import com.doniapr.moviecatalogue.data.Movie
import com.doniapr.moviecatalogue.data.TvShow
import java.util.*

object GenerateData {
    fun generateMovieData(): List<Movie> {
        val movies = ArrayList<Movie>()

        for (movie in Content.movies) {
            movies.add(movie)
        }
        return movies
    }

    fun generateTvShowData(): List<TvShow> {
        val tvShows = ArrayList<TvShow>()

        for (tvShow in Content.tvShows) {
            tvShows.add(tvShow)
        }
        return tvShows
    }
}