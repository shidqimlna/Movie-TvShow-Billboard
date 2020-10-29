package com.example.jetpackprosubmission.util

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {
    private const val RESOURCE = "GLOBAL"

    private val countingIdlingResource = CountingIdlingResource(RESOURCE)

    internal val idlingResource: IdlingResource
        get() = countingIdlingResource

    internal fun increment() {
        countingIdlingResource.increment()
    }

    internal fun decrement() {
        countingIdlingResource.decrement()
    }
}