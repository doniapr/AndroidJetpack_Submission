package com.doniapr.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.doniapr.moviecatalogue.BuildConfig
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.di.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var contentMovie: Movie

    companion object {
        const val DETAIL_ID: String = "detail_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        setSupportActionBar(detail_toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val intent = intent
        contentMovie = intent.getParcelableExtra(DETAIL_ID)

        title_detail_movie.text = contentMovie.title
        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentMovie.posterPath)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.poster_placeholder).into(img_poster_detail)

        val factory = ViewModelFactory.getInstance(this)
        val detailViewModel = ViewModelProvider(
            this,
            factory
        )[DetailMovieViewModel::class.java]
        progress_bar_detail_movie.visibility = View.VISIBLE

        if (contentMovie.id != null) {
            detailViewModel.getDetailMovie(contentMovie.id!!).observe(this, Observer {
                contentMovie = it
                setContent()
                progress_bar_detail_movie.visibility = View.GONE
            })
        }
    }

    private fun setContent() {
        setActionbarTitle("${contentMovie.title}")
        val detailTitle = "${contentMovie.title} (${contentMovie.releaseDate?.slice(0..3)})"
        val runtime = "${contentMovie.runtime} menit"

        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentMovie.posterPath)
            .placeholder(R.drawable.poster_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img_poster_detail)
        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentMovie.backdropPath)
            .placeholder(R.drawable.poster_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img_banner_movie)

        txt_genre_detail.text = contentMovie.genres
        txt_content_overview.text = contentMovie.overview
        txt_content_release_date.text = contentMovie.releaseDate
        txt_content_runtime.text = runtime
        title_detail_movie.text = detailTitle
    }

    private fun setActionbarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar?.title = title
        }
    }
}