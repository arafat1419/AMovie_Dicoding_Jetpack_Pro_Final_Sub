package com.dicoding.sub1_jetpack.ui.home

import android.view.KeyEvent
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.dicoding.sub1_jetpack.R
import com.dicoding.sub1_jetpack.utils.EspressoIdlingResource
import org.junit.*
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun t1loadMovieAndTvShow() {
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(R.id.search_fab))
            .check(matches(isDisplayed()))

        onView(withId(R.id.nav_tv_shows)).perform(click())

        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(R.id.search_fab))
            .check(matches(isDisplayed()))

        onView(withId(R.id.nav_movies)).perform(click())
    }

    @Test
    fun t2detailMovies() {
        onView(withId(R.id.nav_movies)).perform(click())
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )

        onView(withId(R.id.img_prev))
            .check(matches(isDisplayed()))
        onView(withId(R.id.img_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_desc))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_date))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_progress))
            .check(matches(isDisplayed()))
        onView(withId(R.id.fav_fab))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btn_trailer))
            .perform(click())
        Espresso.pressBack()
    }

    @Test
    fun t3detailTvShows() {
        onView(withId(R.id.nav_tv_shows)).perform(click())

        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )

        onView(withId(R.id.img_prev))
            .check(matches(isDisplayed()))
        onView(withId(R.id.img_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_desc))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_date))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_progress))
            .check(matches(isDisplayed()))
        onView(withId(R.id.fav_fab))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btn_trailer))
            .perform(click())
        Espresso.pressBack()
    }

    @Test
    fun t4LoadSearchMovies() {

        onView(withId(R.id.nav_movies)).perform(click())
        onView(withId(R.id.search_fab)).perform(click())
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("batman"),
            pressKey(KeyEvent.KEYCODE_ENTER)
        )
        onView(withId(R.id.rv_search))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_search))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(R.id.rv_search))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.img_prev))
            .check(matches(isDisplayed()))
        onView(withId(R.id.img_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_desc))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_date))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_progress))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btn_trailer))
            .perform(click())
        Espresso.pressBack()
    }

    @Test
    fun t5LoadSearchTvShows() {

        onView(withId(R.id.nav_tv_shows)).perform(click())
        onView(withId(R.id.search_fab)).perform(click())
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("batman"),
            pressKey(KeyEvent.KEYCODE_ENTER)
        )
        onView(withId(R.id.rv_search))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_search))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(R.id.rv_search))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.img_prev))
            .check(matches(isDisplayed()))
        onView(withId(R.id.img_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_desc))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_date))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_progress))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btn_trailer))
            .perform(click())
        Espresso.pressBack()
    }

    @Test
    fun t6InsertAndUpdateMovieFavorite() {
        onView(withId(R.id.nav_movies)).perform(click())
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )
        onView(withId(R.id.fav_fab)).perform(click())
        Espresso.pressBack()

        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withId(R.id.rv_fav_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fav_fab)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun t7InsertAndUpdateTvFavorite() {
        onView(withId(R.id.nav_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )
        onView(withId(R.id.fav_fab)).perform(click())
        Espresso.pressBack()

        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withText(R.string.tab_title_tv)).perform(click())
        onView(withId(R.id.rv_fav_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fav_fab)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun t8MovieSort() {
        onView(withId(R.id.nav_movies)).perform(click())
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.name)).perform(click())

        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.best_rating)).perform(click())

        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.random)).perform(click())

        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
    }

    @Test
    fun t9TvShowSort() {
        onView(withId(R.id.nav_tv_shows)).perform(click())
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.name)).perform(click())

        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.best_rating)).perform(click())

        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.random)).perform(click())

        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
    }
}
