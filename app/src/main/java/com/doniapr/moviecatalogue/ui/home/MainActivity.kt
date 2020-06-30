package com.doniapr.moviecatalogue.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.doniapr.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_toolbar.title = resources.getString(R.string.app_name)
        val viewPagerAdapter =
            ViewPagerAdapter(
                this,
                supportFragmentManager
            )
        viewpager_main.adapter = viewPagerAdapter
        tabs_layout.setupWithViewPager(viewpager_main)
    }
}
