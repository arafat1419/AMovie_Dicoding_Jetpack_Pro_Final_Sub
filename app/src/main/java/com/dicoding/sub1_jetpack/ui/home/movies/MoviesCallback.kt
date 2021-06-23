package com.dicoding.sub1_jetpack.ui.home.movies

import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity

interface MoviesCallback {
    fun onItemClicked(data: MovieEntity)
}