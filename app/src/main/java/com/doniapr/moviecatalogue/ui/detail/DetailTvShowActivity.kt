package com.doniapr.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.doniapr.moviecatalogue.BuildConfig
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.data.source.local.TvShow
import com.doniapr.moviecatalogue.di.ViewModelTvShowFactory
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {
    private var detailId: String? = null
    private lateinit var contentTvShow: TvShow


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        setSupportActionBar(detail_toolbar_tv_show)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val intent = intent
        detailId = intent.getStringExtra(DetailMovieActivity.DETAIL_ID)

        val factory = ViewModelTvShowFactory.getInstance()
        val detailViewModel = ViewModelProvider(
            this,
            factory
        )[DetailTvShowViewModel::class.java]

        if (detailId != null) {
            detailViewModel.getDetailTvShow(detailId!!).observe(this, Observer {
                contentTvShow = it
                setContent()
            })
        }
    }

    private fun setContent() {
        setActionbarTitle("${contentTvShow.title}")
        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentTvShow.posterPath).placeholder(R.drawable.poster_placeholder)
            .into(img_poster_detail_tv_show)
        txt_genre_detail_tv_show.text = contentTvShow.genres.toString()
        txt_content_overview_tv_show.text = contentTvShow.overview
        txt_content_director_tv_show.text = contentTvShow.voteAverage
        txt_content_release_date_tv_show.text = contentTvShow.firstAirDate
        txt_content_runtime_tv_show.text = contentTvShow.episodeRunTime.toString()
    }

    private fun setActionbarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar?.title = title
        }
    }
}