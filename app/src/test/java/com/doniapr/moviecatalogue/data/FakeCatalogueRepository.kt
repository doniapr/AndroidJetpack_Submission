package com.doniapr.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.doniapr.moviecatalogue.data.source.local.LocalDataSource
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
import com.doniapr.moviecatalogue.data.source.remote.RemoteDataSource
import com.doniapr.moviecatalogue.data.source.remote.response.MovieResponse
import com.doniapr.moviecatalogue.data.source.remote.response.TvShowResponse
import com.doniapr.moviecatalogue.utils.AppExecutors

class FakeCatalogueRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    CatalogueDataSource {

    override fun getAllMovie(): LiveData<List<Movie>> {
        val movieResult = MutableLiveData<List<Movie>>()
        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMovieReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<Movie>()
                for (response in movieResponses) {
                    val movie =
                        Movie(
                            response.id!!,
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
                            movieResponse.id!!,
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
                            response.id!!,
                            response.title,
                            response.overview,
                            response.originalTitle,
                            response.genres,
                            response.firstAirDate,
                            response.episodeRunTime?.get(0),
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
                            tvShowResponse.id!!,
                            tvShowResponse.title,
                            tvShowResponse.overview,
                            tvShowResponse.originalTitle,
                            tvShowResponse.genres,
                            tvShowResponse.firstAirDate,
                            tvShowResponse.episodeRunTime?.get(0),
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

    override fun getFavoriteMovie(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false).setInitialLoadSizeHint(4).setPageSize(4).build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShow>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false).setInitialLoadSizeHint(4).setPageSize(4).build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun getDetailFavoriteMovie(id: String): LiveData<Movie> =
        localDataSource.getDetailFavoriteMovie(id)

    override fun getDetailFavoriteTvShow(id: String): LiveData<TvShow> =
        localDataSource.getDetailFavoriteTvShow(id)

    override fun addFavoriteMovie(movie: Movie) {
        appExecutors.diskIO().execute { localDataSource.addFavoriteMovie(movie) }
    }

    override fun addFavoriteTvShow(tvShow: TvShow) {
        appExecutors.diskIO().execute { localDataSource.addFavoriteTvShow(tvShow) }
    }

    override fun unFavoriteMovie(movie: Movie) {
        appExecutors.diskIO().execute { localDataSource.unFavoriteMovie(movie) }
    }

    override fun unFavoriteTvShow(tvShow: TvShow) {
        appExecutors.diskIO().execute { localDataSource.unFavoriteTvShow(tvShow) }
    }

}