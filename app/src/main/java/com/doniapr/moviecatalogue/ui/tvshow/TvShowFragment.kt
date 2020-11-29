package com.doniapr.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.di.ViewModelFactory
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
            val factory = ViewModelFactory.getInstance(requireActivity())
            val tvShowViewModel = ViewModelProvider(
                this,
                factory
            )[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()
            progressbar_tv_show.visibility = View.VISIBLE

            tvShowViewModel.getTvShow().observe(this, Observer {
                tvShowAdapter.setData(it)
                tvShowAdapter.notifyDataSetChanged()
                progressbar_tv_show.visibility = View.GONE
            })

            with(rv_tv_show) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }

    }

}
