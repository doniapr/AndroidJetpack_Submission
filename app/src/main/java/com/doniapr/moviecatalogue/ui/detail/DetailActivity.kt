package com.doniapr.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.data.Movie
import com.doniapr.moviecatalogue.data.TvShow
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var type: String? = null
    private var detailId: String? = null
    private lateinit var contentMovie: Movie
    private lateinit var contentTvShow: TvShow

    companion object {
        const val TYPE: String = "type"
        const val DETAIL_ID: String = "detail_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(detail_toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val intent = intent
        type = intent.getStringExtra(TYPE)
        detailId = intent.getStringExtra(DETAIL_ID)

        val detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        if (type != null && detailId != null) {
            if (type.equals(resources.getString(R.string.movie))) {
                contentMovie = detailViewModel.getDetailMovie(detailId)
                setContent(true)
            } else if (type.equals(resources.getString(R.string.tv_show))) {
                contentTvShow = detailViewModel.getDetailTvShow(detailId)
                setContent(false)
            }
        }
    }

    private fun setContent(isMovie: Boolean) {
        if (isMovie) {
            setActionbarTitle("${contentMovie.title} (${contentMovie.year})")
            Glide.with(this).load(contentMovie.poster).into(img_poster_detail)
            txt_genre_detail.text = contentMovie.genre
            txt_content_overview.text = contentMovie.overview
            txt_content_director.text = contentMovie.director
            txt_content_release_date.text = contentMovie.releaseDate
            txt_content_runtime.text = contentMovie.runtime
        } else {
            setActionbarTitle("${contentTvShow.title} (${contentTvShow.year})")
            Glide.with(this).load(contentTvShow.poster).into(img_poster_detail)
            txt_genre_detail.text = contentTvShow.genre
            txt_content_overview.text = contentTvShow.overview
            txt_content_director.text = contentTvShow.director
            txt_content_release_date.text = contentTvShow.releaseDate
            txt_content_runtime.text = contentTvShow.runtime
        }
    }

    private fun setActionbarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar?.title = title
        }
    }
}