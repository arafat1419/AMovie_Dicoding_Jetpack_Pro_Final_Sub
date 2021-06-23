package com.dicoding.sub1_jetpack.di.home

import com.dicoding.sub1_jetpack.di.favorite.FavoriteFragmentBuildersModule
import com.dicoding.sub1_jetpack.ui.home.favorite.FavoriteFragment
import com.dicoding.sub1_jetpack.ui.home.movies.MoviesFragment
import com.dicoding.sub1_jetpack.ui.home.tvshows.TvShowsFragment
import com.dicoding.sub1_jetpack.ui.search.movies.SearchMovieFragment
import com.dicoding.sub1_jetpack.ui.search.tvShows.SearchTvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowsFragment(): TvShowsFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchMoviesFragment(): SearchMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchTvShowsFragment(): SearchTvShowFragment

    @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
    abstract fun contributeFavoriteFragment(): FavoriteFragment

}