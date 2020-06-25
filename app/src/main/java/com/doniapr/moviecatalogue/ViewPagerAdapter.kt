package com.doniapr.moviecatalogue

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.doniapr.moviecatalogue.movie.MovieFragment
import com.doniapr.moviecatalogue.tvshow.TvShowFragment

class ViewPagerAdapter (private val context: Context, fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    @StringRes
    private val tabTitles = intArrayOf(
        R.string.tab_movie_text,
        R.string.tab_tv_show_text
    )

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment?= null
        when(position){
            0 -> fragment = MovieFragment()
            1 -> fragment = TvShowFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabTitles[position])
    }

    override fun getCount(): Int = tabTitles.size

}