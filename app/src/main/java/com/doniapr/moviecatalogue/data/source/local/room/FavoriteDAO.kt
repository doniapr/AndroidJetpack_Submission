package com.doniapr.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM movies")
    fun getFavoriteMovie(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movies WHERE id=:id")
    fun getDetailFavoriteMovie(id: String): LiveData<Movie>

    @Query("SELECT * FROM tv_shows")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShow>

    @Query("SELECT * FROM tv_shows WHERE id=:id")
    fun getDetailFavoriteTvShow(id: String): LiveData<TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun favoriteMovie(movies: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun favoriteTvShow(movies: TvShow)

    @Delete
    fun unFavoriteMovie(movie: Movie)

    @Delete
    fun unFavoriteTvShow(tvShow: TvShow)

}