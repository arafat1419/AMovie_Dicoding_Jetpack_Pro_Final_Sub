package com.dicoding.sub1_jetpack.ui.home.tvshows

import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity

interface TvShowsCallback {
    fun onItemClicked(data: TvEntity)
}