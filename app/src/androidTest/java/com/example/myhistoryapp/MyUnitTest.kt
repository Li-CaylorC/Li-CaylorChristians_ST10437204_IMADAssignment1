package com.example.myhistoryapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test

@LargeTest
class MyUnitTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun searchButtonDisplaysResult() {
        onView(withId(R.id.edAge)).perform(typeText("30"), closeSoftKeyboard())
        onView(withId(R.id.edRange)).perform(typeText("5"), closeSoftKeyboard())
        onView(withId(R.id.btnSearch)).perform(click())
        onView(withId(R.id.tvResult)).check(matches(withText("")))
    }

    @Test
    fun clearButtonClearsEditText() {
        onView(withId(R.id.edAge)).perform(typeText("30"), closeSoftKeyboard())
        onView(withId(R.id.btnClear)).perform(click())
        onView(withId(R.id.edAge)).check(matches(withText("")))
    }

    @Test
    fun ageLessThanTwentyDisplaysToast() {
        onView(withId(R.id.edAge)).perform(typeText("18"), closeSoftKeyboard())
        onView(withId(R.id.btnSearch)).perform(click())
        onView(withText("Only ages greater than or equal to 20 allowed")).check(matches(isDisplayed()))
    }
}
