package com.doniapr.moviecatalogue.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doniapr.moviecatalogue.data.TvShowRepository
import com.doniapr.moviecatalogue.ui.detail.DetailTvShowViewModel
import com.doniapr.moviecatalogue.ui.tvshow.TvShowViewModel

class ViewModelTvShowFactory private constructor(private val tvShowRepository: TvShowRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelTvShowFactory? = null

        fun getInstance(): ViewModelTvShowFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelTvShowFactory(Injection.provideTvShowRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(tvShowRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                return DetailTvShowViewModel(tvShowRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}