package com.doniapr.moviecatalogue.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.ui.favorite.FavoriteFragment
import com.doniapr.moviecatalogue.ui.movie.MovieFragment
import com.doniapr.moviecatalogue.ui.tvshow.TvShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navigationListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.navigation_movie -> {
                    fragment = MovieFragment()
                    changeFragment(fragment)
                    main_toolbar.title = resources.getString(R.string.movie)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_tv_show -> {
                    fragment = TvShowFragment()
                    changeFragment(fragment)
                    main_toolbar.title = resources.getString(R.string.tv_show)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorite -> {
                    fragment = FavoriteFragment()
                    changeFragment(fragment)
                    main_toolbar.title = resources.getString(R.string.favorite)

                    return@OnNavigationItemSelectedListener true
                }
                else -> throw Throwable("Unknown item Id")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_toolbar.title = resources.getString(R.string.app_name)
        nav_view.setOnNavigationItemSelectedListener(navigationListener)
        if (savedInstanceState == null) {
            nav_view.selectedItemId = R.id.navigation_movie;
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}
