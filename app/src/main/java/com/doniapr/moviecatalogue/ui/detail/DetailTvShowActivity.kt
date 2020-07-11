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
import com.doniapr.moviecatalogue.data.source.local.TvShow
import com.doniapr.moviecatalogue.di.ViewModelTvShowFactory
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {
    private var detailTv: TvShow? = null
    private lateinit var contentTvShow: TvShow


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        setSupportActionBar(detail_toolbar_tv_show)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val intent = intent
        detailTv = intent.getParcelableExtra(DetailMovieActivity.DETAIL_ID)
        title_detail_tv_show.text = detailTv?.title
        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + detailTv?.posterPath)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.poster_placeholder)
            .into(img_poster_detail_tv_show)

        val reviewAdapter = ReviewAdapter()
        val factory = ViewModelTvShowFactory.getInstance()
        val detailViewModel = ViewModelProvider(
            this,
            factory
        )[DetailTvShowViewModel::class.java]

        progress_bar_detail_tv_show.visibility = View.VISIBLE
        progress_bar_review_tv_show.visibility = View.VISIBLE
        if (detailTv?.id != null) {
            detailViewModel.getDetailTvShow(detailTv?.id!!).observe(this, Observer {
                contentTvShow = it
                progress_bar_detail_tv_show.visibility = View.GONE
                setContent()
            })
            detailViewModel.getReview(detailTv?.id!!).observe(this, Observer {
                if (it != null && it.isNotEmpty()) {
                    reviewAdapter.setData(it)
                    reviewAdapter.notifyDataSetChanged()
                } else {
                    rv_review_tv_show.visibility = View.GONE
                    tv_no_review_tv_show.visibility = View.VISIBLE
                }
                progress_bar_review_tv_show.visibility = View.GONE
            })
        }
        with(rv_review_tv_show) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = reviewAdapter
        }
    }

    private fun setContent() {
        setActionbarTitle("${contentTvShow.title}")
        val detailTitle = "${contentTvShow.title} (${contentTvShow.firstAirDate?.slice(0..3)})"
        val runtime = "${contentTvShow.episodeRunTime?.get(0)} menit"

        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentTvShow.posterPath)
            .into(img_poster_detail_tv_show)
        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentTvShow.backdropPath)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.poster_placeholder).into(img_banner_tv_show)

        txt_content_overview_tv_show.text = contentTvShow.overview
        txt_content_release_date_tv_show.text = contentTvShow.firstAirDate
        txt_content_runtime_tv_show.text = runtime
        title_detail_tv_show.text = detailTitle

        var listGenre = ""
        for (genre in contentTvShow.genres!!) {
            listGenre = "${listGenre}${genre.name}\n"
        }
        txt_genre_detail_tv_show.text = listGenre
    }

    private fun setActionbarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar?.title = title
        }
    }
}