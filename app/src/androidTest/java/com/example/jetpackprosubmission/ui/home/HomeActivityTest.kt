package com.example.jetpackprosubmission.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.util.IdlingResource
import com.example.jetpackprosubmission.util.TitleUtil
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    lateinit var movieTitle: String
    lateinit var tvShowTitle: String

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(ViewMatchers.withId(R.id.fragment_movie_recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.fragment_movie_recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadDetailMovie() {
        onView(ViewMatchers.withId(R.id.fragment_movie_recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.fragment_movie_recyclerView)).also {
            movieTitle = TitleUtil.getMovieTitle(it, 0)
        }.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(ViewMatchers.withId(R.id.activity_detail_movie_tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.activity_detail_movie_tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(movieTitle)))
    }

    @Test
    fun loadDetailFavoriteMovie() {
        onView(ViewMatchers.withId(R.id.fragment_movie_recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.fragment_movie_recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(ViewMatchers.withId(R.id.activity_detail_movie_fab_favorite)).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(ViewMatchers.withId(R.id.activity_main_fab_favorite)).perform(
            ViewActions.click()
        )
        onView(ViewMatchers.withId(R.id.fragment_fav_movie_recyclerView)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        onView(ViewMatchers.withId(R.id.fragment_fav_movie_recyclerView)).also {
            movieTitle = TitleUtil.getMovieTitle(it, 0)
        }.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(ViewMatchers.withId(R.id.activity_detail_movie_tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.activity_detail_movie_tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(movieTitle)))
        onView(ViewMatchers.withId(R.id.activity_detail_movie_fab_favorite)).perform(ViewActions.click())
    }

    @Test
    fun loadTvShows() {
        onView(ViewMatchers.withId(R.id.menu_tvshow)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment_tvShow_recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.fragment_tvShow_recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadDetailTvShow() {
        onView(ViewMatchers.withId(R.id.menu_tvshow)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment_tvShow_recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.fragment_tvShow_recyclerView)).also {
            tvShowTitle = TitleUtil.getTvShowTitle(it, 0)
        }.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(ViewMatchers.withId(R.id.activity_detail_tvshow_tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.activity_detail_tvshow_tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(tvShowTitle)))
    }

    @Test
    fun loadDetailFavoriteTvShow() {
        onView(ViewMatchers.withId(R.id.menu_tvshow)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment_tvShow_recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.fragment_tvShow_recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(ViewMatchers.withId(R.id.activity_detail_tvshow_fab_favorite)).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(ViewMatchers.withId(R.id.activity_main_fab_favorite)).perform(
            ViewActions.click()
        )
        onView(ViewMatchers.withId(R.id.menu_tvshow)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment_fav_tvShow_recyclerView)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        onView(ViewMatchers.withId(R.id.fragment_fav_tvShow_recyclerView)).also {
            tvShowTitle = TitleUtil.getTvShowTitle(it, 0)
        }.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(ViewMatchers.withId(R.id.activity_detail_tvshow_tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.activity_detail_tvshow_tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(tvShowTitle)))
        onView(ViewMatchers.withId(R.id.activity_detail_tvshow_fab_favorite)).perform(ViewActions.click())
    }
}