package com.doniapr.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.doniapr.moviecatalogue.BuildConfig
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.data.source.local.entity.Movie
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
import com.doniapr.moviecatalogue.di.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import kotlinx.android.synthetic.main.fragment_movie.*

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var contentTvShow: TvShow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)


        setSupportActionBar(detail_toolbar_tv_show)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val intent = intent
        contentTvShow = intent.getParcelableExtra(DetailMovieActivity.DETAIL_ID)

        title_detail_tv_show.text = contentTvShow.title
        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentTvShow.posterPath)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.poster_placeholder).into(img_poster_detail_tv_show)

        val factory = ViewModelFactory.getInstance(this)
        val detailViewModel = ViewModelProvider(
            this,
            factory
        )[DetailTvShowViewModel::class.java]
        progress_bar_detail_tv_show.visibility = View.VISIBLE

        if (contentTvShow.id != null) {
            detailViewModel.getDetailTvShow(contentTvShow.id!!).observe(this, Observer {
                contentTvShow = it
                setContent()
                progress_bar_detail_tv_show.visibility = View.GONE
            })
        }
    }

    private fun setContent() {
        setActionbarTitle("${contentTvShow.title}")
        val detailTitle = "${contentTvShow.title} (${contentTvShow.firstAirDate?.slice(0..3)})"
        val runtime = "${contentTvShow.episodeRunTime?.get(0)} menit"

        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentTvShow.posterPath)
            .placeholder(R.drawable.poster_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img_poster_detail_tv_show)
        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentTvShow.backdropPath)
            .placeholder(R.drawable.poster_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img_banner_tv_show)

        txt_genre_detail_tv_show.text = contentTvShow.genres
        txt_content_overview_tv_show.text = contentTvShow.overview
        txt_content_release_date_tv_show.text = contentTvShow.firstAirDate
        txt_content_runtime_tv_show.text = runtime
        title_detail_tv_show.text = detailTitle
    }

    private fun setActionbarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar?.title = title
        }
    }
}