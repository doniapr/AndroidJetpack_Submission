package com.doniapr.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.doniapr.moviecatalogue.utils.GenerateData
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val movies = GenerateData.generateMovieData()
    private val tvShow = GenerateData.generateTvShowData()


    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        Thread.sleep(5000)
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movies.size))
        Thread.sleep(5000)
    }

    @Test
    fun loadTvShow(){
        onView(withText(R.string.tab_tv_show_text)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        Thread.sleep(5000)
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShow.size))
        Thread.sleep(5000)
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(5000)
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText(R.string.tab_tv_show_text)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(5000)
    }
}