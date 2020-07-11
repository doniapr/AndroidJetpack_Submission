package com.doniapr.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.doniapr.moviecatalogue.BuildConfig
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.data.source.local.Movie
import com.doniapr.moviecatalogue.di.ViewModelMovieFactory
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private var detailMovie: Movie? = null
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
        detailMovie = intent.getParcelableExtra(DETAIL_ID)
        title_detail_movie.text = detailMovie?.title
        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + detailMovie?.posterPath)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.poster_placeholder).into(img_poster_detail)

        val reviewAdapter = ReviewAdapter()
        val factory = ViewModelMovieFactory.getInstance()
        val detailViewModel = ViewModelProvider(
            this,
            factory
        )[DetailMovieViewModel::class.java]

        progress_bar_detail_movie.visibility = View.VISIBLE
        progress_bar_review_movie.visibility = View.VISIBLE
        if (detailMovie?.id != null) {
            detailViewModel.getDetailMovie(detailMovie?.id!!).observe(this, Observer {
                contentMovie = it
                progress_bar_detail_movie.visibility = View.GONE
                setContent()
            })

            detailViewModel.getReview(detailMovie?.id!!).observe(this, Observer {
                if (it != null && it.isNotEmpty()) {
                    reviewAdapter.setData(it)
                    reviewAdapter.notifyDataSetChanged()
                } else {
                    rv_review_movie.visibility = View.GONE
                    tv_no_review.visibility = View.VISIBLE
                }
                progress_bar_review_movie.visibility = View.GONE
            })
        }
        with(rv_review_movie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = reviewAdapter
        }
    }

    private fun setContent() {
        setActionbarTitle("${contentMovie.title}")
        val detailTitle = "${contentMovie.title} (${contentMovie.releaseDate?.slice(0..3)})"
        val runtime = "${contentMovie.runtime} menit"

        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentMovie.posterPath)
            .into(img_poster_detail)
        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentMovie.backdropPath)
            .placeholder(R.drawable.poster_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade()).into(img_banner_movie)
        txt_content_overview.text = contentMovie.overview
        txt_content_release_date.text = contentMovie.releaseDate
        txt_content_runtime.text = runtime
        title_detail_movie.text = detailTitle

        var listGenre = ""
        for (genre in contentMovie.genres!!) {
            listGenre = "${listGenre}${genre.name}\n"
        }
        txt_genre_detail.text = listGenre
    }

    private fun setActionbarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar?.title = title
        }
    }
}