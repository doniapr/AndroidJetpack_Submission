package com.doniapr.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
import com.doniapr.moviecatalogue.data.source.remote.RemoteDataSource
import com.doniapr.moviecatalogue.data.source.remote.response.MovieResponse
import com.doniapr.moviecatalogue.data.source.remote.response.TvShowResponse

class CatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource): CatalogueDataSource{

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): CatalogueRepository =
            instance ?: synchronized(this){
                instance ?: CatalogueRepository(remoteDataSource)
            }
    }
    override fun getAllMovie(): LiveData<List<Movie>> {
        val movieResult = MutableLiveData<List<Movie>>()
        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMoviesCallback{
            override fun onAllMovieReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<Movie>()
                for (response in movieResponses){
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

        remoteDataSource.getDetailMovie(id, object : RemoteDataSource.GetDetailMovie{
            override fun onMovieReceived(response: MovieResponse?) {
                if (response != null){
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

                    movieResult.postValue(movie)
                }
            }
        })

        return movieResult
    }

    override fun getAllTvShow(): LiveData<List<TvShow>> {
        val tvShowResult = MutableLiveData<List<TvShow>>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowsCallback{
            override fun onAllTvShowReceived(tvShowResponses: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShow>()
                for (response in tvShowResponses){
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

        remoteDataSource.getDetailTvShow(id, object : RemoteDataSource.GetDetailTvShow{
            override fun onTvShowReceived(response: TvShowResponse?) {
                if (response != null){
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
                            response.backdropPath,
                            response.numberOfEpisodes,
                            response.numberOfSeasons
                        )

                    tvShowResult.postValue(tvShow)
                }
            }
        })

        return tvShowResult
    }

}