package com.doniapr.moviecatalogue.utils

import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
import com.doniapr.moviecatalogue.data.source.remote.response.MovieResponse
import com.doniapr.moviecatalogue.data.source.remote.response.TvShowResponse
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

    fun generateRemoteMovieData(): List<MovieResponse> {
        val movies = ArrayList<MovieResponse>()

        for (movie in Content.movies) {
            val movieResponse = MovieResponse(
                movie.id,
                movie.title,
                movie.overview,
                movie.originalTitle,
                movie.genres,
                movie.releaseDate,
                movie.runtime,
                movie.tagline,
                movie.status,
                movie.voteAverage,
                movie.voteCount,
                movie.posterPath,
                movie.backdropPath
            )
            movies.add(movieResponse)
        }
        return movies
    }

    fun getRemoteDetailMovie(id: String): MovieResponse {
        val movie = Content.movies.filter {
            it.id == id
        }
        return MovieResponse(
            movie[0].id,
            movie[0].title,
            movie[0].overview,
            movie[0].originalTitle,
            movie[0].genres,
            movie[0].releaseDate,
            movie[0].runtime,
            movie[0].tagline,
            movie[0].status,
            movie[0].voteAverage,
            movie[0].voteCount,
            movie[0].posterPath,
            movie[0].backdropPath
        )
    }

    fun generateRemoteTvShowData(): List<TvShowResponse> {
        val tvShows = ArrayList<TvShowResponse>()

        for (tvShow in Content.tvShows) {
            val tvShowResponse = TvShowResponse(
                tvShow.id,
                tvShow.title,
                tvShow.overview,
                tvShow.originalTitle,
                tvShow.genres,
                tvShow.firstAirDate,
                listOf(tvShow.episodeRunTime!!),
                tvShow.inProduction,
                tvShow.status,
                tvShow.voteAverage,
                tvShow.voteCount,
                tvShow.posterPath,
                tvShow.backdropPath,
                tvShow.numberOfEpisodes,
                tvShow.numberOfSeasons
            )
            tvShows.add(tvShowResponse)
        }
        return tvShows
    }

    fun getRemoteDetailTvShow(id: String): TvShowResponse {
        val tvShow = Content.tvShows.filter {
            it.id == id
        }

        return TvShowResponse(
            tvShow[0].id,
            tvShow[0].title,
            tvShow[0].overview,
            tvShow[0].originalTitle,
            tvShow[0].genres,
            tvShow[0].firstAirDate,
            listOf(tvShow[0].episodeRunTime!!),
            tvShow[0].inProduction,
            tvShow[0].status,
            tvShow[0].voteAverage,
            tvShow[0].voteCount,
            tvShow[0].posterPath,
            tvShow[0].backdropPath,
            tvShow[0].numberOfEpisodes,
            tvShow[0].numberOfSeasons
        )
    }
}