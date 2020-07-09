package com.doniapr.moviecatalogue.data.source.remote

import com.doniapr.moviecatalogue.data.source.local.Movie
import com.doniapr.moviecatalogue.data.source.local.TvShow
import com.doniapr.moviecatalogue.data.source.remote.apiservice.MainApi
import com.doniapr.moviecatalogue.data.source.remote.response.MovieResponse
import com.doniapr.moviecatalogue.data.source.remote.response.ReviewResponse
import com.doniapr.moviecatalogue.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    // MOVIE
    fun getNowPlayingMovie(responseCallback: MovieResponseCallback){
        GlobalScope.launch {
            val result = MainApi().mainApi.getNowPlayingMovie()
            if (result.isSuccessful){
                if (result.code() == 200) {
                    responseCallback.onSuccess(result.body())
                } else {
                    responseCallback.onFailed(result.message())
                }
            }
        }
    }

    fun getPopularMovie(responseCallback: MovieResponseCallback){
        GlobalScope.launch {
            val result = MainApi().mainApi.getPopularMovie()
            if (result.isSuccessful){
                if (result.code() == 200) {
                    responseCallback.onSuccess(result.body())
                } else {
                    responseCallback.onFailed(result.message())
                }
            }
        }
    }

    fun getUpcomingMovie(responseCallback: MovieResponseCallback){
        GlobalScope.launch {
            val result = MainApi().mainApi.getUpcomingMovie()
            if (result.isSuccessful){
                if (result.code() == 200) {
                    responseCallback.onSuccess(result.body())
                } else {
                    responseCallback.onFailed(result.message())
                }
            }
        }
    }

    fun getSearchMovie(query: String, responseCallback: MovieResponseCallback){
        GlobalScope.launch {
            val result = MainApi().mainApi.getSearchMovie(query)
            if (result.isSuccessful){
                if (result.code() == 200) {
                    responseCallback.onSuccess(result.body())
                } else {
                    responseCallback.onFailed(result.message())
                }
            }
        }
    }

    fun getReviewMovie(id: String, callback: ReviewCallback){
        GlobalScope.launch {
            val result = MainApi().mainApi.getReviewMovie(id)
            if (result.isSuccessful){
                if (result.code() == 200) {
                    callback.onSuccess(result.body())
                } else {
                    callback.onFailed(result.message())
                }
            }
        }
    }

    fun getDetailMovie(id: String, callback: MovieCallback){
        GlobalScope.launch {
            val result = MainApi().mainApi.getDetailMovie(id)
            if (result.isSuccessful){
                if (result.code() == 200) {
                    callback.onSuccess(result.body())
                } else {
                    callback.onFailed(result.message())
                }
            }
        }
    }

    // TV SHOW
    fun getOnAirTvShow(responseCallback: TvShowResponseCallback){
        GlobalScope.launch {
            val result = MainApi().mainApi.getOnAirTvShow()
            if (result.isSuccessful){
                if (result.code() == 200) {
                    responseCallback.onSuccess(result.body())
                } else {
                    responseCallback.onFailed(result.message())
                }
            }
        }
    }

    fun getAiringTodayTvShow(responseCallback: TvShowResponseCallback){
        GlobalScope.launch {
            val result = MainApi().mainApi.getAiringTodayTvShow()
            if (result.isSuccessful){
                if (result.code() == 200) {
                    responseCallback.onSuccess(result.body())
                } else {
                    responseCallback.onFailed(result.message())
                }
            }
        }
    }

    fun getPopularTvShow(responseCallback: TvShowResponseCallback){
        GlobalScope.launch {
            val result = MainApi().mainApi.getPopularTvShow()
            if (result.isSuccessful){
                if (result.code() == 200) {
                    responseCallback.onSuccess(result.body())
                } else {
                    responseCallback.onFailed(result.message())
                }
            }
        }
    }

    fun getSearchTvShow(query: String, responseCallback: TvShowResponseCallback){
        GlobalScope.launch {
            val result = MainApi().mainApi.getSearchTvShow(query)
            if (result.isSuccessful){
                if (result.code() == 200) {
                    responseCallback.onSuccess(result.body())
                } else {
                    responseCallback.onFailed(result.message())
                }
            }
        }
    }

    fun getDetailTvShow(id: String, responseCallback: TvShowCallback){
        GlobalScope.launch {
            val result = MainApi().mainApi.getDetailTvShow(id)
            if (result.isSuccessful){
                if (result.code() == 200) {
                    responseCallback.onSuccess(result.body())
                } else {
                    responseCallback.onFailed(result.message())
                }
            }
        }
    }

    fun getReviewTvShow(id: String, responseCallback: ReviewCallback){
        GlobalScope.launch {
            val result = MainApi().mainApi.getReviewTvShow(id)
            if (result.isSuccessful){
                if (result.code() == 200) {
                    responseCallback.onSuccess(result.body())
                } else {
                    responseCallback.onFailed(result.message())
                }
            }
        }
    }

    interface MovieResponseCallback{
        fun onSuccess(movieResponses: MovieResponse?)
        fun onFailed(message: String)
    }

    interface MovieCallback{
        fun onSuccess(movies: Movie?)
        fun onFailed(message: String)
    }

    interface TvShowResponseCallback{
        fun onSuccess(tvShowResponses: TvShowResponse?)
        fun onFailed(message: String)
    }

    interface TvShowCallback{
        fun onSuccess(tvShow: TvShow?)
        fun onFailed(message: String)
    }

    interface ReviewCallback{
        fun onSuccess(review: ReviewResponse?)
        fun onFailed(message: String)
    }
}