package com.dicoding.sub1_jetpack.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.sub1_jetpack.R
import com.dicoding.sub1_jetpack.databinding.ActivityHomeBinding
import com.dicoding.sub1_jetpack.ui.home.favorite.FavoriteFragment
import com.dicoding.sub1_jetpack.ui.home.movies.MoviesFragment
import com.dicoding.sub1_jetpack.ui.home.tvshows.TvShowsFragment
import com.dicoding.sub1_jetpack.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class HomeActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewModel()
        setupBottomNavBar()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(
            this@HomeActivity,
            factory
        )[HomeViewModel::class.java]
    }

    private fun setupBottomNavBar() {
        val moviesFragment = MoviesFragment()
        val tvShowsFragment = TvShowsFragment()
        val favoriteFragment = FavoriteFragment()

        setActiveFragment(moviesFragment, resources.getString(R.string.tab_title_1))

        binding.bottomNavbar.setNavigationChangeListener { view, _ ->
            when (view.id) {
                R.id.nav_movies -> {
                    setActiveFragment(
                        moviesFragment,
                        resources.getString(R.string.tab_title_1)
                    )
                }
                R.id.nav_tv_shows -> {
                    setActiveFragment(
                        tvShowsFragment,
                        resources.getString(R.string.tab_title_2)
                    )
                }
                R.id.nav_favorite -> {
                    setActiveFragment(
                        favoriteFragment,
                        resources.getString(R.string.tab_title_3)
                    )
                }
            }
        }
    }

    private fun setActiveFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
        }.commit()

        setActionBarTitle(title)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}