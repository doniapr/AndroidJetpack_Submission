package com.doniapr.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.di.ViewModelTvShowFactory
import kotlinx.android.synthetic.main.fragment_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelTvShowFactory.getInstance()
            val tvShowViewModel = ViewModelProvider(
                this,
                factory
            )[TvShowViewModel::class.java]

            val onAirAdapter = TvShowAdapter()
            val airingTodayAdapter = TvShowAdapter()
            val popularAdapter = TvShowAdapter()

            progressbar_on_air.visibility = View.VISIBLE
            progressbar_airing_today.visibility = View.VISIBLE
            progressbar_popular_tv.visibility = View.VISIBLE

            tvShowViewModel.getOnAirTvShow().observe(this, Observer {
                onAirAdapter.setData(it)
                onAirAdapter.notifyDataSetChanged()
                progressbar_on_air.visibility = View.GONE
            })

            tvShowViewModel.getAiringTodayTvShow().observe(this, Observer {
                airingTodayAdapter.setData(it)
                airingTodayAdapter.notifyDataSetChanged()
                progressbar_airing_today.visibility = View.GONE
            })

            tvShowViewModel.getPopularTvShow().observe(this, Observer {
                popularAdapter.setData(it)
                popularAdapter.notifyDataSetChanged()
                progressbar_popular_tv.visibility = View.GONE
            })

            with(rv_tv_show_on_air) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = onAirAdapter
            }

            with(rv_tv_show_airing_today) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = airingTodayAdapter
            }

            with(rv_tv_show_popular) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = popularAdapter
            }
        }
    }

}
