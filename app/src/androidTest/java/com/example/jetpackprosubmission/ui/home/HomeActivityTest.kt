package com.example.jetpackprosubmission.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.util.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.fragment_movie_recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.fragment_movie_recyclerView))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(ViewMatchers.withId(R.id.fragment_movie_recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                )
        )
        Espresso.onView(ViewMatchers.withId(R.id.activity_detail_movie_tv_title))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.activity_detail_movie_tv_title))
                .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].title)))
    }

    @Test
    fun loadTvShows() {
        Espresso.onView(ViewMatchers.withId(R.id.menu_tvshow)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment_tvShow_recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.fragment_tvShow_recyclerView))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        Espresso.onView(ViewMatchers.withId(R.id.menu_tvshow)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment_tvShow_recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                )
        )
        Espresso.onView(ViewMatchers.withId(R.id.activity_detail_tvshow_tv_title))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.activity_detail_tvshow_tv_title))
                .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].name)))
    }
}