package com.dicoding.sub1_jetpack.di

import com.dicoding.sub1_jetpack.di.home.HomeFragmentBuildersModule
import com.dicoding.sub1_jetpack.ui.detail.DetailActivity
import com.dicoding.sub1_jetpack.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity

}