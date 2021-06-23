package com.dicoding.sub1_jetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.sub1_jetpack.data.model.DataTrailer
import com.dicoding.sub1_jetpack.data.source.CatalogRepository
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity> =
        catalogRepository.getDetailMovie(movieId)

    fun getDetailTvShows(tvShowId: Int): LiveData<TvEntity> =
        catalogRepository.getDetailTvShow(tvShowId)

    fun getTrailerMovie(movieId: Int): LiveData<DataTrailer> =
        catalogRepository.getTrailerMovie(movieId)

    fun getTrailerTv(tvId: Int): LiveData<DataTrailer> =
        catalogRepository.getTrailerTvShow(tvId)

    fun getDetailSearchMovie(movieId: Int): LiveData<MovieEntity> =
        catalogRepository.getDetailSearchMovie(movieId)

    fun getDetailSearchTv(tvId: Int): LiveData<TvEntity> =
        catalogRepository.getDetailSearchTvShows(tvId)

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        catalogRepository.setFavMovie(movie, state)
    }

    fun setFavoriteTvShow(tvShow: TvEntity, state: Boolean) {
        catalogRepository.setFavTvShow(tvShow, state)
    }
}