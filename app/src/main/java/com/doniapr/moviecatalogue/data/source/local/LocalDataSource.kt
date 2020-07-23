package com.doniapr.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
import com.doniapr.moviecatalogue.data.source.local.room.FavoriteDAO

class LocalDataSource private constructor(private val favoriteDAO: FavoriteDAO){
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favoriteDAO: FavoriteDAO): LocalDataSource =
            INSTANCE ?: LocalDataSource(favoriteDAO)
    }
    
    fun getFavoriteMovie(): LiveData<List<Movie>> = favoriteDAO.getFavoriteMovie()

    fun getDetailFavoriteMovie(id: String): LiveData<Movie> = favoriteDAO.getDetailFavoriteMovie(id)

    fun getFavoriteTvShow(): LiveData<List<TvShow>> = favoriteDAO.getFavoriteTvShow()

    fun getDetailFavoriteTvShow(id: String): LiveData<TvShow> = favoriteDAO.getDetailFavoriteTvShow(id)

    fun addFavoriteMovie(movie: Movie) {
        favoriteDAO.favoriteMovie(movie)
    }

    

    fun addFavoriteTvShow(tvShow: TvShow) {
        favoriteDAO.favoriteTvShow(tvShow)
    }

    fun unFavoriteMovie(movie: Movie) {
        favoriteDAO.unFavoriteMovie(movie)
    }

    fun unFavoriteTvShow(tvShow: TvShow) {
        favoriteDAO.unFavoriteTvShow(tvShow)
    }

}