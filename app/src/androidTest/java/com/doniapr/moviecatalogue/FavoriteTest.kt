package com.doniapr.moviecatalogue

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.doniapr.moviecatalogue.ui.home.MainActivity
import com.doniapr.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteTest {
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
    fun loadFavoriteMovie() {
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.rv_favorite_movie))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteTvShow() {
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withText(R.string.tab_tv_show_text))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withText(R.string.tab_tv_show_text))
            .perform(click())
        onView(withId(R.id.rv_favorite_tv_show))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}