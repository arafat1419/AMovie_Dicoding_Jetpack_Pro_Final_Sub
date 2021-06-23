package com.dicoding.sub1_jetpack.ui.home.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.sub1_jetpack.data.source.CatalogRepository
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    fun getListFavMovies(): LiveData<PagedList<MovieEntity>> = catalogRepository.getListFavMovies()

    fun getListFavTvShows(): LiveData<PagedList<TvEntity>> = catalogRepository.getListFavTvShows()
}