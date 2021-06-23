package com.dicoding.sub1_jetpack.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import com.dicoding.sub1_jetpack.data.source.local.room.CatalogDao
import com.dicoding.sub1_jetpack.utils.Sorting
import com.dicoding.sub1_jetpack.utils.Sorting.MOVIE_ENTITIES
import com.dicoding.sub1_jetpack.utils.Sorting.TV_SHOW_ENTITIES
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val catalogDao: CatalogDao) {

    fun getListMovies(sort: String): DataSource.Factory<Int, MovieEntity> =
        catalogDao.getListMovies(Sorting.getSortedQuery(sort, MOVIE_ENTITIES))

    fun getListFavMovies(): DataSource.Factory<Int, MovieEntity> = catalogDao.getListFavMovies()

    fun getListTvShows(sort: String): DataSource.Factory<Int, TvEntity> =
        catalogDao.getListTvShows(Sorting.getSortedQuery(sort, TV_SHOW_ENTITIES))

    fun getListFavTvShows(): DataSource.Factory<Int, TvEntity> = catalogDao.getListFavTvShows()

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity> = catalogDao.getDetailMovieById(movieId)

    fun getDetailTvShow(tvShowId: Int): LiveData<TvEntity> =
        catalogDao.getDetailTvShowById(tvShowId)

    fun insertMovies(movies: List<MovieEntity>) = catalogDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvEntity>) = catalogDao.insertTvShows(tvShows)

    fun setFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        catalogDao.updateMovie(movie)
    }

    fun setFavTvShow(tvShow: TvEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        catalogDao.updateTvShow(tvShow)
    }
}