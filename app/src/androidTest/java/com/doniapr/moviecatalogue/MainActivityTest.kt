package com.doniapr.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.doniapr.moviecatalogue.ui.home.MainActivity
import com.doniapr.moviecatalogue.utils.EspressoIdlingResource
import com.doniapr.moviecatalogue.utils.GenerateData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val movies = GenerateData.generateRemoteMovieData()
    private val tvShow = GenerateData.generateRemoteTvShowData()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movies.size - 1
            )
        )
    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.navigation_tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvShow.size - 1
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.txt_content_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_content_overview)).check(matches(withText(movies[0].overview)))
    }

    @Test
    fun doAddFavoriteMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.txt_content_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_content_overview)).check(matches(withText(movies[0].overview)))
        onView(withId(R.id.action_favorite)).perform(click())
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.navigation_tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.txt_content_overview_tv_show)).check(matches(isDisplayed()))
    }

    @Test
    fun doAddFavoriteTvShow() {
        onView(withId(R.id.navigation_tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.txt_content_overview_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.action_favorite)).perform(click())
    }
}