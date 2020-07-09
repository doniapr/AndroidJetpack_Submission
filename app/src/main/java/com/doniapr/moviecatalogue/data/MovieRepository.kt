package com.doniapr.moviecatalogue.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doniapr.moviecatalogue.data.source.local.Movie
import com.doniapr.moviecatalogue.data.source.local.Review
import com.doniapr.moviecatalogue.data.source.remote.RemoteDataSource
import com.doniapr.moviecatalogue.data.source.remote.response.MovieResponse
import com.doniapr.moviecatalogue.data.source.remote.response.ReviewResponse

class MovieRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance
                    ?: MovieRepository(remoteDataSource)
            }
    }

    override fun getNowPlayingMovie(): LiveData<List<Movie>> {
        val moviesResult = MutableLiveData<List<Movie>>()
        remoteDataSource.getNowPlayingMovie(object : RemoteDataSource.MovieResponseCallback {
            override fun onSuccess(movieResponses: MovieResponse?) {
                val movies = ArrayList<Movie>()
                if (movieResponses?.movies != null) {
                    for (response in movieResponses.movies!!) {
                        movies.add(response)
                    }
                }
                moviesResult.postValue(movies)
            }

            override fun onFailed(message: String) {
                Log.e("onFailed()", message)
            }
        })

        return moviesResult
    }

    override fun getPopularMovie(): LiveData<List<Movie>> {
        val moviesResult = MutableLiveData<List<Movie>>()
        remoteDataSource.getPopularMovie(object : RemoteDataSource.MovieResponseCallback {
            override fun onSuccess(movieResponses: MovieResponse?) {
                val movies = ArrayList<Movie>()
                if (movieResponses?.movies != null) {
                    for (response in movieResponses.movies!!) {
                        movies.add(response)
                    }
                }
                moviesResult.postValue(movies)
            }

            override fun onFailed(message: String) {
                Log.e("onFailed()", message)
            }
        })

        return moviesResult
    }

    override fun getUpcomingMovie(): LiveData<List<Movie>> {
        val moviesResult = MutableLiveData<List<Movie>>()
        remoteDataSource.getUpcomingMovie(object : RemoteDataSource.MovieResponseCallback {
            override fun onSuccess(movieResponses: MovieResponse?) {
                val movies = ArrayList<Movie>()
                if (movieResponses?.movies != null) {
                    for (response in movieResponses.movies!!) {
                        movies.add(response)
                    }
                }
                moviesResult.postValue(movies)
            }

            override fun onFailed(message: String) {
                Log.e("onFailed()", message)
            }
        })

        return moviesResult
    }

    override fun getSearchMovie(query: String): LiveData<List<Movie>> {
        val moviesResult = MutableLiveData<List<Movie>>()
        remoteDataSource.getSearchMovie(query, object : RemoteDataSource.MovieResponseCallback {
            override fun onSuccess(movieResponses: MovieResponse?) {
                val movies = ArrayList<Movie>()
                if (movieResponses?.movies != null) {
                    for (response in movieResponses.movies!!) {
                        movies.add(response)
                    }
                }
                moviesResult.postValue(movies)
            }

            override fun onFailed(message: String) {
                Log.e("onFailed()", message)
            }
        })

        return moviesResult
    }

    override fun getDetailMovie(movieId: String): LiveData<Movie> {
        val movieResult = MutableLiveData<Movie>()
        remoteDataSource.getDetailMovie(movieId, object : RemoteDataSource.MovieCallback {
            override fun onSuccess(movies: Movie?) {
                val movie =
                    Movie(
                        movies?.id,
                        movies?.title,
                        movies?.overview,
                        movies?.originalTitle,
                        movies?.genres,
                        movies?.releaseDate,
                        movies?.runtime,
                        movies?.tagline,
                        movies?.status,
                        movies?.voteAverage,
                        movies?.voteCount,
                        movies?.posterPath,
                        movies?.backdropPath
                    )
                movieResult.postValue(movie)
            }

            override fun onFailed(message: String) {
                Log.e("onFailed()", message)
            }
        })

        return movieResult
    }

    override fun getReviewMovie(movieId: String): LiveData<List<Review>> {
        val reviewResult = MutableLiveData<List<Review>>()
        remoteDataSource.getReviewMovie(movieId, object : RemoteDataSource.ReviewCallback {
            override fun onSuccess(review: ReviewResponse?) {
                val reviews = ArrayList<Review>()
                if (review?.reviews != null) {
                    for (response in review.reviews!!) {
                        reviews.add(response)
                    }
                }
                reviewResult.postValue(reviews)
            }


            override fun onFailed(message: String) {
                Log.e("onFailed()", message)
            }
        })

        return reviewResult
    }

}