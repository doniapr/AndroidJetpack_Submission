package com.doniapr.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.di.ViewModelMovieFactory
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelMovieFactory.getInstance()
            val movieViewModel = ViewModelProvider(
                this,
                factory
            )[MovieViewModel::class.java]

            val nowPlayingAdapter = MovieAdapter()
            val popularAdapter = MovieAdapter()
            val upcomingAdapter = MovieAdapter()

            progressbar_now_playing.visibility = View.VISIBLE
            progressbar_popular.visibility = View.VISIBLE
            progressbar_upcoming.visibility = View.VISIBLE

            movieViewModel.getNowPlayingMovie().observe(this, Observer {
                nowPlayingAdapter.setData(it)
                nowPlayingAdapter.notifyDataSetChanged()
                progressbar_now_playing.visibility = View.GONE
            })

            movieViewModel.getPopularMovie().observe(this, Observer {
                popularAdapter.setData(it)
                popularAdapter.notifyDataSetChanged()
                progressbar_popular.visibility = View.GONE
            })

            movieViewModel.getUpcomingMovie().observe(this, Observer {
                upcomingAdapter.setData(it)
                upcomingAdapter.notifyDataSetChanged()
                progressbar_upcoming.visibility = View.GONE
            })

            with(rv_movie_now_playing) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = nowPlayingAdapter
            }

            with(rv_movie_popular) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = popularAdapter
            }

            with(rv_movie_upcoming) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = upcomingAdapter
            }
        }
    }

}
