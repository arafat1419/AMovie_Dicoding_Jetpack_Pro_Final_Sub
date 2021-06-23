package com.dicoding.sub1_jetpack.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.sub1_jetpack.data.source.CatalogRepository
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import com.dicoding.sub1_jetpack.data.source.local.voLocal.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    fun getListMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> =
        catalogRepository.getListMovies(sort)

    fun getListTvShows(sort: String): LiveData<Resource<PagedList<TvEntity>>> =
        catalogRepository.getListTvShows(sort)

    fun getSearchMovies(query: String): LiveData<List<MovieEntity>> =
        catalogRepository.getSearchMovies(query)

    fun getSearchTvShows(query: String): LiveData<List<TvEntity>> =
        catalogRepository.getSearchTvShows(query)

}