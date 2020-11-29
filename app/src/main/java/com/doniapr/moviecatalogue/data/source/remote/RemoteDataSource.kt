package com.doniapr.moviecatalogue.data.source.remote

import android.os.Handler
import com.doniapr.moviecatalogue.data.source.remote.response.MovieResponse
import com.doniapr.moviecatalogue.data.source.remote.response.TvShowResponse
import com.doniapr.moviecatalogue.utils.EspressoIdlingResource
import com.doniapr.moviecatalogue.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler()

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllMovie(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllMovieReceived(jsonHelper.loadMovie())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )

    }

    fun getDetailMovie(id: String, callback: GetDetailMovie) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onMovieReceived(jsonHelper.getDetailMovie(id))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getAllTvShow(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllTvShowReceived(jsonHelper.loadTvShow())

                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )

    }

    fun getDetailTvShow(id: String, callback: GetDetailTvShow) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onTvShowReceived(jsonHelper.getDetailTvShow(id))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadMoviesCallback {
        fun onAllMovieReceived(movieResponses: List<MovieResponse>)
    }

    interface GetDetailMovie {
        fun onMovieReceived(movieResponse: MovieResponse?)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowReceived(tvShowResponses: List<TvShowResponse>)
    }

    interface GetDetailTvShow {
        fun onTvShowReceived(tvShowResponse: TvShowResponse?)
    }

}