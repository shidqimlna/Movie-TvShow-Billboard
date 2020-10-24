package com.example.jetpackprosubmission.ui.view.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.util.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovies()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadCourses() {
        Espresso.onView(withId(R.id.fragment_movie_recyclerView))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.fragment_movie_recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadDetailCourse() {
        Espresso.onView(withId(R.id.fragment_movie_recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.activity_detail_movie_tv_title))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.activity_detail_movie_tv_title))
            .check(ViewAssertions.matches(withText(dummyMovie[0].title)))
    }
}