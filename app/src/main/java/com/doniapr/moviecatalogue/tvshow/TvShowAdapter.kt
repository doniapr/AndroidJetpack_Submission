package com.doniapr.moviecatalogue.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.data.TvShow
import kotlinx.android.synthetic.main.item_layout.view.*

class TvShowAdapter(
    private val tvShows: List<TvShow>,
    private val listener: (TvShow) -> Unit
) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = tvShows[position]
        holder.bind(tvShow, listener)
    }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShow, listener: (TvShow) -> Unit) {
            with(itemView) {
                this.setOnClickListener { listener(tvShow) }
                val textTitle = "${tvShow.title} (${tvShow.year})"
                txt_title.text = textTitle
                Glide.with(this.context).load(tvShow.poster).into(img_poster)
            }
        }
    }
}