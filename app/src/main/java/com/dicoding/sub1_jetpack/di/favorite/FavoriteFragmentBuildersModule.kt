package com.dicoding.sub1_jetpack.di.favorite

import com.dicoding.sub1_jetpack.ui.home.favorite.movies.FavMoviesFragment
import com.dicoding.sub1_jetpack.ui.home.favorite.tvShows.FavTvShowsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFavoriteMovieFragment(): FavMoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteTvShowFragment(): FavTvShowsFragment
}