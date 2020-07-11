package com.doniapr.moviecatalogue.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doniapr.moviecatalogue.data.MovieRepository
import com.doniapr.moviecatalogue.ui.detail.DetailMovieViewModel
import com.doniapr.moviecatalogue.ui.movie.MovieViewModel

class ViewModelMovieFactory private constructor(private val movieRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelMovieFactory? = null

        fun getInstance(): ViewModelMovieFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelMovieFactory(Injection.provideMovieRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                return DetailMovieViewModel(movieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}