package com.doniapr.moviecatalogue.ui.favorite

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
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*

class FavoriteTvShowFragment : Fragment() {
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        favoriteViewModel = ViewModelProvider(
            requireActivity(),
            factory
        )[FavoriteViewModel::class.java]
        progressbar_favorite_tv_show.visibility = View.VISIBLE
        val favoriteAdapter = FavoriteTvShowAdapter()

        favoriteViewModel.getTvShowDataList().observe(requireActivity(), Observer {
            favoriteAdapter.submitList(it)
            favoriteAdapter.notifyDataSetChanged()
            progressbar_favorite_tv_show.visibility = View.GONE
            if (it == null || it.size == 0) {
                txt_no_favorite_tv_show.visibility = View.VISIBLE
            }
        })
        with(rv_favorite_tv_show) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }

    }
}