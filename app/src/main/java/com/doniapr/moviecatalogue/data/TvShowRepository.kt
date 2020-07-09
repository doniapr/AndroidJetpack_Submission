package com.doniapr.moviecatalogue.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doniapr.moviecatalogue.data.source.local.Review
import com.doniapr.moviecatalogue.data.source.local.TvShow
import com.doniapr.moviecatalogue.data.source.remote.RemoteDataSource
import com.doniapr.moviecatalogue.data.source.remote.response.ReviewResponse
import com.doniapr.moviecatalogue.data.source.remote.response.TvShowResponse

class TvShowRepository(private val remoteDataSource: RemoteDataSource) : TvShowDataSource {
    companion object {
        @Volatile
        private var instance: TvShowRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): TvShowRepository =
            instance ?: synchronized(this) {
                instance
                    ?: TvShowRepository(remoteDataSource)
            }
    }

    override fun getOnAirTvShow(): LiveData<List<TvShow>> {
        val tvShowResult = MutableLiveData<List<TvShow>>()
        remoteDataSource.getOnAirTvShow(object : RemoteDataSource.TvShowResponseCallback {
            override fun onSuccess(tvShowResponses: TvShowResponse?) {
                val tvShows = ArrayList<TvShow>()
                if (tvShowResponses?.tvShow != null) {
                    for (response in tvShowResponses.tvShow!!) {
                        tvShows.add(response)
                    }
                }
                tvShowResult.postValue(tvShows)
            }

            override fun onFailed(message: String) {
                Log.e("onFailed()", message)
            }
        })

        return tvShowResult
    }

    override fun getAiringTodayTvShow(): LiveData<List<TvShow>> {
        val tvShowResult = MutableLiveData<List<TvShow>>()
        remoteDataSource.getAiringTodayTvShow(object : RemoteDataSource.TvShowResponseCallback {
            override fun onSuccess(tvShowResponses: TvShowResponse?) {
                val tvShows = ArrayList<TvShow>()
                if (tvShowResponses?.tvShow != null) {
                    for (response in tvShowResponses.tvShow!!) {
                        tvShows.add(response)
                    }
                }
                tvShowResult.postValue(tvShows)
            }

            override fun onFailed(message: String) {
                Log.e("onFailed()", message)
            }
        })

        return tvShowResult
    }

    override fun getPopularTvShow(): LiveData<List<TvShow>> {
        val tvShowResult = MutableLiveData<List<TvShow>>()
        remoteDataSource.getPopularTvShow(object : RemoteDataSource.TvShowResponseCallback {
            override fun onSuccess(tvShowResponses: TvShowResponse?) {
                val tvShows = ArrayList<TvShow>()
                if (tvShowResponses?.tvShow != null) {
                    for (response in tvShowResponses.tvShow!!) {
                        tvShows.add(response)
                    }
                }
                tvShowResult.postValue(tvShows)
            }

            override fun onFailed(message: String) {
                Log.e("onFailed()", message)
            }
        })

        return tvShowResult
    }

    override fun getSearchTvShow(query: String): LiveData<List<TvShow>> {
        val tvShowResult = MutableLiveData<List<TvShow>>()
        remoteDataSource.getSearchTvShow(query, object : RemoteDataSource.TvShowResponseCallback {
            override fun onSuccess(tvShowResponses: TvShowResponse?) {
                val tvShows = ArrayList<TvShow>()
                if (tvShowResponses?.tvShow != null) {
                    for (response in tvShowResponses.tvShow!!) {
                        tvShows.add(response)
                    }
                }
                tvShowResult.postValue(tvShows)
            }

            override fun onFailed(message: String) {
                Log.e("onFailed()", message)
            }
        })

        return tvShowResult
    }

    override fun getDetailTvShow(id: String): LiveData<TvShow> {
        val tvShowResult = MutableLiveData<TvShow>()
        remoteDataSource.getDetailTvShow(id, object : RemoteDataSource.TvShowCallback {
            override fun onSuccess(tvShow: TvShow?) {
                if (tvShow != null) {
                    tvShowResult.postValue(tvShow)
                }
            }

            override fun onFailed(message: String) {
                Log.e("onFailed()", message)
            }
        })

        return tvShowResult
    }

    override fun getReviewTvShow(id: String): LiveData<List<Review>> {
        val tvShowReview = MutableLiveData<List<Review>>()
        remoteDataSource.getReviewTvShow(id, object : RemoteDataSource.ReviewCallback {
            override fun onSuccess(review: ReviewResponse?) {
                val reviews = ArrayList<Review>()
                if (review?.reviews != null) {
                    for (response in review.reviews!!) {
                        reviews.add(response)
                    }
                }
                tvShowReview.postValue(reviews)
            }

            override fun onFailed(message: String) {
                Log.e("onFailed()", message)
            }
        })

        return tvShowReview
    }

}