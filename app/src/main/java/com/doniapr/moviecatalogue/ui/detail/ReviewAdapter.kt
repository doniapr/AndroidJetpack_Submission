package com.doniapr.moviecatalogue.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.data.source.local.Review
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    private var reviews: List<Review> = ArrayList()

    fun setData(reviews: List<Review>) {
        this.reviews = reviews
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun getItemCount(): Int = reviews.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(review: Review) {
            with(itemView) {
                tv_review_author.text = review.author
                tv_review_content.text = review.content
            }
        }
    }
}