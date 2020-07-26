package com.doniapr.moviecatalogue.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.doniapr.moviecatalogue.BuildConfig
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.data.source.local.entity.TvShow
import com.doniapr.moviecatalogue.ui.detail.DetailMovieActivity
import com.doniapr.moviecatalogue.ui.detail.DetailTvShowActivity
import kotlinx.android.synthetic.main.item_layout.view.*

class FavoriteTvShowAdapter internal constructor() :
    PagedListAdapter<TvShow, FavoriteTvShowAdapter.TvShowViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return TvShowViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        holder.bind(tvShow)
    }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShow?) {
            with(itemView) {
                this.setOnClickListener {
                    val intent = Intent(context, DetailTvShowActivity::class.java).apply {
                        putExtra(DetailMovieActivity.DETAIL_ID, tvShow)
                    }
                    context?.startActivity(intent)
                }
                val textTitle = "${tvShow?.title} (${tvShow?.firstAirDate?.slice(0..3)})"
                txt_title.text = textTitle
                Glide.with(this.context).load(BuildConfig.BASE_URL_IMAGE + tvShow?.posterPath)
                    .placeholder(R.drawable.poster_placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(img_poster)
            }
        }
    }
}