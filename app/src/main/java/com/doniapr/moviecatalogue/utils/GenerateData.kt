package com.doniapr.moviecatalogue.utils

import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.data.Movie
import com.doniapr.moviecatalogue.data.TvShow
import java.util.ArrayList

object GenerateData {
    fun generateMovieData(): List<Movie>{
        val movies = ArrayList<Movie>()

        for (movie in Content.movies){
            movies.add(movie)
        }
        return movies
    }

    fun generateDetailMovieData(title: String): Movie{
        val movies: List<Movie> = Content.movies.filter {
            it.title == title
        }

        return movies[0]
    }

    fun generateTvShowData(): List<TvShow>{
        val tvShows = ArrayList<TvShow>()

        for (tvShow in Content.tvShows){
            tvShows.add(tvShow)
        }
        return tvShows
    }

    fun generateDetailTvShowData(title: String): TvShow {
        val tvShows: List<TvShow> = Content.tvShows.filter {
            it.title == title
        }

        return tvShows[0]
    }
}