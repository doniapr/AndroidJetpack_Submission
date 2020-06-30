package com.doniapr.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.ui.detail.DetailActivity
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val movieViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]
            val movies = movieViewModel.getMovie()

            val adapter =
                MovieAdapter(movies) {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.DETAIL_ID, it.title)
                        putExtra(DetailActivity.TYPE, context?.resources?.getString(R.string.movie))
                    }
                    context?.startActivity(intent)
                }

            with(rv_movie) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

}
