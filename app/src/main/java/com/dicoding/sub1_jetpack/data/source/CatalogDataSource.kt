package com.dicoding.sub1_jetpack.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.sub1_jetpack.data.model.DataTrailer
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import com.dicoding.sub1_jetpack.data.source.local.voLocal.Resource

interface CatalogDataSource {

    fun getSearchMovies(query: String): LiveData<List<MovieEntity>>

    fun getListMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity>

    fun getDetailSearchMovie(movieId: Int): LiveData<MovieEntity>

    fun getTrailerMovie(movieId: Int): LiveData<DataTrailer>

    fun getListFavMovies(): LiveData<PagedList<MovieEntity>>

    fun setFavMovie(movie: MovieEntity, state: Boolean)

    fun getSearchTvShows(query: String): LiveData<List<TvEntity>>

    fun getListTvShows(sort: String): LiveData<Resource<PagedList<TvEntity>>>

    fun getDetailTvShow(tvShowId: Int): LiveData<TvEntity>

    fun getDetailSearchTvShows(tvShowId: Int): LiveData<TvEntity>

    fun getTrailerTvShow(tvId: Int): LiveData<DataTrailer>

    fun getListFavTvShows(): LiveData<PagedList<TvEntity>>

    fun setFavTvShow(tvShow: TvEntity, state: Boolean)
}