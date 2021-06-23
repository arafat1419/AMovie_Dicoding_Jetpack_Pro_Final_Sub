package com.dicoding.sub1_jetpack.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity

@Dao
interface CatalogDao {

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getListMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [TvEntity::class])
    fun getListTvShows(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM movies_entities WHERE isFavorite = 1")
    fun getListFavMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvShows_entities WHERE isFavorite = 1")
    fun getListFavTvShows(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM movies_entities WHERE id = :movieId")
    fun getDetailMovieById(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tvShows_entities WHERE id = :tvShowId")
    fun getDetailTvShowById(tvShowId: Int): LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvEntity::class)
    fun insertTvShows(tvShows: List<TvEntity>)

    @Update(entity = MovieEntity::class)
    fun updateMovie(movie: MovieEntity)

    @Update(entity = TvEntity::class)
    fun updateTvShow(tvShows: TvEntity)
}