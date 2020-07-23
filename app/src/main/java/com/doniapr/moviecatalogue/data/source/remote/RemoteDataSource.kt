package com.doniapr.moviecatalogue.data.source.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
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

    fun getAllMovie(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        handler.postDelayed(
            {
                result.value = ApiResponse.success(jsonHelper.loadMovie())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return result
    }

    fun getDetailMovie(id: String) : LiveData<ApiResponse<MovieResponse?>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<MovieResponse?>>()
        handler.postDelayed(
            {
                result.value = ApiResponse.success(jsonHelper.getDetailMovie(id))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )

        return result
    }

    fun getAllTvShow(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        handler.postDelayed(
            {
                result.value = ApiResponse.success(jsonHelper.loadTvShow())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
         return result
    }

    fun getDetailTvShow(id: String): LiveData<ApiResponse<TvShowResponse?>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<TvShowResponse?>>()
        handler.postDelayed(
            {
                result.value = ApiResponse.success(jsonHelper.getDetailTvShow(id))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )

        return result
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