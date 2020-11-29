package com.doniapr.moviecatalogue.di

import android.content.Context
import com.doniapr.moviecatalogue.data.CatalogueRepository
import com.doniapr.moviecatalogue.data.source.local.LocalDataSource
import com.doniapr.moviecatalogue.data.source.local.room.FavoriteDatabase
import com.doniapr.moviecatalogue.data.source.remote.RemoteDataSource
import com.doniapr.moviecatalogue.utils.AppExecutors
import com.doniapr.moviecatalogue.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {
        val database = FavoriteDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.favoriteDao())
        val appExecutors = AppExecutors()

        return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}