package com.doniapr.moviecatalogue.utils

import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
import java.util.*

object GenerateData {
    fun generateMovieData(): List<Movie> {
        val movies = ArrayList<Movie>()

        for (movie in Content.movies) {
            movies.add(movie)
        }
        return movies
    }

    fun getDetailMovie(id: String): Movie {
        val movies = Content.movies.filter {
            it.id == id
        }

        return movies[0]
    }

    fun generateTvShowData(): List<TvShow> {
        val tvShows = ArrayList<TvShow>()

        for (tvShow in Content.tvShows) {
            tvShows.add(tvShow)
        }
        return tvShows
    }

    fun getDetailTvShow(id: String): TvShow {
        val tvshows = Content.tvShows.filter {
            it.id == id
        }

        return tvshows[0]
    }
}