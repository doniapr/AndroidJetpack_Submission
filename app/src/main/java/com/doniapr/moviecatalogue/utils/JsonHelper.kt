package com.doniapr.moviecatalogue.utils

import android.content.Context
import com.doniapr.moviecatalogue.data.source.remote.response.MovieResponse
import com.doniapr.moviecatalogue.data.source.remote.response.TvShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {
    private fun parseFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()

            String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun loadMovie(): List<MovieResponse> {
        val listMovie = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parseFileToString("movie.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val overview = movie.getString("overview")
                val genres = movie.getString("genres")
                val releaseDate = movie.getString("release_date")
                val voteCount = movie.getString("vote_count")
                val originalTitle = movie.getString("original_title")
                val voteAverage = movie.getString("vote_average")
                val posterPath = movie.getString("poster_path")
                val backdropPath = movie.getString("backdrop_path")

                val movieResponse = MovieResponse(
                    id,
                    title,
                    overview,
                    originalTitle,
                    genres,
                    releaseDate,
                    null,
                    null,
                    null,
                    voteAverage,
                    voteCount,
                    posterPath,
                    backdropPath
                )
                listMovie.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return listMovie
    }

    fun getDetailMovie(id: String): MovieResponse? {
        val fileName = String.format("movie_%s.json", id)
        var movieResponse: MovieResponse? = null
        try {
            val result = parseFileToString(fileName)
            if (result != null) {
                val movie = JSONObject(result)
                val idMovie = movie.getString("id")
                val title = movie.getString("title")
                val overview = movie.getString("overview")
                val genres = movie.getString("genres")
                val releaseDate = movie.getString("release_date")
                val voteCount = movie.getString("vote_count")
                val originalTitle = movie.getString("original_title")
                val voteAverage = movie.getString("vote_average")
                val runtime = movie.getString("runtime")
                val tagline = movie.getString("tagline")
                val status = movie.getString("status")
                val posterPath = movie.getString("poster_path")
                val backdropPath = movie.getString("backdrop_path")

                movieResponse = MovieResponse(
                    idMovie,
                    title,
                    overview,
                    originalTitle,
                    genres,
                    releaseDate,
                    runtime,
                    tagline,
                    status,
                    voteAverage,
                    voteCount,
                    posterPath,
                    backdropPath
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return movieResponse
    }

    fun loadTvShow(): List<TvShowResponse> {
        val listTvShow = ArrayList<TvShowResponse>()
        try {
            val responseObject = JSONObject(parseFileToString("tvshow.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val id = tvShow.getString("id")
                val title = tvShow.getString("name")
                val overview = tvShow.getString("overview")
                val genres = tvShow.getString("genres")
                val firstAirDate = tvShow.getString("first_air_date")
                val voteCount = tvShow.getString("vote_count")
                val originalTitle = tvShow.getString("original_name")
                val voteAverage = tvShow.getString("vote_average")
                val posterPath = tvShow.getString("poster_path")
                val backdropPath = tvShow.getString("backdrop_path")

                val tvShowResponse = TvShowResponse(
                    id,
                    title,
                    overview,
                    originalTitle,
                    genres,
                    firstAirDate,
                    null,
                    null,
                    null,
                    voteAverage,
                    voteCount,
                    posterPath,
                    backdropPath
                )
                listTvShow.add(tvShowResponse)


            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return listTvShow
    }

    fun getDetailTvShow(id: String): TvShowResponse? {
        val fileName = String.format("tvshow_%s.json", id)
        var tvShowResponse: TvShowResponse? = null
        try {
            val result = parseFileToString(fileName)
            if (result != null) {
                val tvShow = JSONObject(result)
                val idTvShow = tvShow.getString("id")
                val title = tvShow.getString("name")
                val overview = tvShow.getString("overview")
                val genres = tvShow.getString("genres")
                val firstAirDate = tvShow.getString("first_air_date")
                val voteCount = tvShow.getString("vote_count")
                val inProduction = tvShow.getBoolean("in_production")
                val status = tvShow.getString("status")
                val originalTitle = tvShow.getString("original_name")
                val voteAverage = tvShow.getString("vote_average")
                val posterPath = tvShow.getString("poster_path")
                val backdropPath = tvShow.getString("backdrop_path")
                val numberOfEpisode = tvShow.getString("number_of_episodes")
                val numberOfSeason = tvShow.getString("number_of_seasons")

                val episodeRuntime = tvShow.getJSONArray("episode_run_time")
                val runtime: List<String> = listOf(episodeRuntime.get(0).toString())

                tvShowResponse = TvShowResponse(
                    idTvShow,
                    title,
                    overview,
                    originalTitle,
                    genres,
                    firstAirDate,
                    runtime,
                    inProduction,
                    status,
                    voteAverage,
                    voteCount,
                    posterPath,
                    backdropPath,
                    numberOfEpisode,
                    numberOfSeason
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return tvShowResponse
    }
}