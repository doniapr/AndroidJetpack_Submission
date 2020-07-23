package com.doniapr.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doniapr.moviecatalogue.data.source.local.LocalDataSource
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
import com.doniapr.moviecatalogue.data.source.remote.ApiResponse
import com.doniapr.moviecatalogue.data.source.remote.RemoteDataSource
import com.doniapr.moviecatalogue.data.source.remote.response.MovieResponse
import com.doniapr.moviecatalogue.data.source.remote.response.TvShowResponse
import com.doniapr.moviecatalogue.utils.AppExecutors
import com.doniapr.moviecatalogue.vo.Resource

class CatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    CatalogueDataSource {

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    override fun getAllMovie(): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<Movie>> = localDataSource.getFavoriteMovie()

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<Movie>()
                for (response in data) {
                    val movie =
                        Movie(
                            response.id,
                            response.title,
                            response.overview,
                            response.originalTitle,
                            response.genres,
                            response.releaseDate,
                            response.runtime,
                            response.tagline,
                            response.status,
                            response.voteAverage,
                            response.voteCount,
                            response.posterPath,
                            response.backdropPath
                        )

                    movieList.add(movie)
                }

                localDataSource.addFavoriteMovie()

            }

        }


        val movieResult = MutableLiveData<List<Movie>>()
        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMovieReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<Movie>()
                for (response in movieResponses) {
                    val movie =
                        Movie(
                            response.id,
                            response.title,
                            response.overview,
                            response.originalTitle,
                            response.genres,
                            response.releaseDate,
                            response.runtime,
                            response.tagline,
                            response.status,
                            response.voteAverage,
                            response.voteCount,
                            response.posterPath,
                            response.backdropPath
                        )

                    movieList.add(movie)
                }
                movieResult.postValue(movieList)
            }
        })

        return movieResult
    }

    override fun getDetailMovie(id: String): LiveData<Movie> {
        val movieResult = MutableLiveData<Movie>()

        remoteDataSource.getDetailMovie(id, object : RemoteDataSource.GetDetailMovie {
            override fun onMovieReceived(movieResponse: MovieResponse?) {
                if (movieResponse != null) {
                    val movie =
                        Movie(
                            movieResponse.id,
                            movieResponse.title,
                            movieResponse.overview,
                            movieResponse.originalTitle,
                            movieResponse.genres,
                            movieResponse.releaseDate,
                            movieResponse.runtime,
                            movieResponse.tagline,
                            movieResponse.status,
                            movieResponse.voteAverage,
                            movieResponse.voteCount,
                            movieResponse.posterPath,
                            movieResponse.backdropPath
                        )

                    movieResult.postValue(movie)
                }
            }
        })

        return movieResult
    }

    override fun getAllTvShow(): LiveData<List<TvShow>> {
        val tvShowResult = MutableLiveData<List<TvShow>>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowReceived(tvShowResponses: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShow>()
                for (response in tvShowResponses) {
                    val tvShow =
                        TvShow(
                            response.id,
                            response.title,
                            response.overview,
                            response.originalTitle,
                            response.genres,
                            response.firstAirDate,
                            response.episodeRunTime,
                            response.inProduction,
                            response.status,
                            response.voteAverage,
                            response.voteCount,
                            response.posterPath,
                            response.backdropPath
                        )

                    tvShowList.add(tvShow)
                }
                tvShowResult.postValue(tvShowList)
            }
        })

        return tvShowResult
    }

    override fun getDetailTvShow(id: String): LiveData<TvShow> {
        val tvShowResult = MutableLiveData<TvShow>()

        remoteDataSource.getDetailTvShow(id, object : RemoteDataSource.GetDetailTvShow {
            override fun onTvShowReceived(tvShowResponse: TvShowResponse?) {
                if (tvShowResponse != null) {
                    val tvShow =
                        TvShow(
                            tvShowResponse.id,
                            tvShowResponse.title,
                            tvShowResponse.overview,
                            tvShowResponse.originalTitle,
                            tvShowResponse.genres,
                            tvShowResponse.firstAirDate,
                            tvShowResponse.episodeRunTime,
                            tvShowResponse.inProduction,
                            tvShowResponse.status,
                            tvShowResponse.voteAverage,
                            tvShowResponse.voteCount,
                            tvShowResponse.posterPath,
                            tvShowResponse.backdropPath,
                            tvShowResponse.numberOfEpisodes,
                            tvShowResponse.numberOfSeasons
                        )

                    tvShowResult.postValue(tvShow)
                }
            }
        })

        return tvShowResult
    }

    override fun setMovieFavorite(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun setTvShowFavorite(tvShow: TvShow) {
        TODO("Not yet implemented")
    }

}