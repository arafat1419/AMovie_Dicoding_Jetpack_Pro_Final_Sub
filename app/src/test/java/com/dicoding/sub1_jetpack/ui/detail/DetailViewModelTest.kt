package com.dicoding.sub1_jetpack.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.sub1_jetpack.data.model.DataTrailer
import com.dicoding.sub1_jetpack.data.source.CatalogRepository
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import com.dicoding.sub1_jetpack.data.source.local.voLocal.Resource
import com.dicoding.sub1_jetpack.utils.DataDummyModel
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val listDummyMovie = DataDummyModel.generateDummyMovies()
    private val listDummyTvShow = DataDummyModel.generateDummyShows()
    private val listDummyMovieTrailer = DataDummyModel.generateDummyMovieTrailer()
    private val listDummyTvTrailer = DataDummyModel.generateDummyTvShowTrailer()
    private val dummyMovie = listDummyMovie[1]
    private val movieId = dummyMovie.id
    private val dummyTvShow = listDummyTvShow[1]
    private val tvShowId = dummyTvShow.id
    private val dummyMovieTrailer = listDummyMovieTrailer[1]
    private val dummyTvTrailer = listDummyTvTrailer[1]

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvObserver: Observer<Resource<TvEntity>>

    @Mock
    private lateinit var observerTv: Observer<TvEntity>

    @Mock
    private lateinit var observerTrailer: Observer<DataTrailer>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDummy = MutableLiveData<MovieEntity>()
        movieDummy.value = dummyMovie

        Mockito.`when`(catalogRepository.getDetailMovie(movieId)).thenReturn(movieDummy)

        val data = viewModel.getDetailMovie(movieId).value as MovieEntity

        assertNotNull(data)
        assertEquals(dummyMovie.id, data.id)
        assertEquals(dummyMovie.title, data.title)
        assertEquals(dummyMovie.overview, data.overview)
        assertEquals(dummyMovie.poster, data.poster)
        assertEquals(dummyMovie.imgPreview, data.imgPreview)
        assertEquals(dummyMovie.rating.toString(), data.rating.toString())
        assertEquals(dummyMovie.releaseDate, data.releaseDate)
        assertEquals(dummyMovie.isFavorite, data.isFavorite)

        viewModel.getDetailMovie(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)

    }

    @Test
    fun getTvShowDetail() {
        val tvShowDummy = MutableLiveData<TvEntity>()
        tvShowDummy.value = dummyTvShow

        Mockito.`when`(catalogRepository.getDetailTvShow(tvShowId)).thenReturn(tvShowDummy)

        val data = viewModel.getDetailTvShows(tvShowId).value as TvEntity

        assertEquals(dummyTvShow.id, data.id)
        assertEquals(dummyTvShow.title, data.title)
        assertEquals(dummyTvShow.overview, data.overview)
        assertEquals(dummyTvShow.poster, data.poster)
        assertEquals(dummyTvShow.imgPreview, data.imgPreview)
        assertEquals(dummyTvShow.rating.toString(), data.rating.toString())
        assertEquals(dummyTvShow.releaseDate, data.releaseDate)
        assertEquals(dummyTvShow.isFavorite, data.isFavorite)

        viewModel.getDetailTvShows(tvShowId).observeForever(observerTv)
        verify(observerTv).onChanged(dummyTvShow)
    }

    @Test
    fun getSearchMovieDetail() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        Mockito.`when`(catalogRepository.getDetailSearchMovie(movieId)).thenReturn(movie)

        val data = viewModel.getDetailSearchMovie(movieId).value as MovieEntity

        assertNotNull(data)
        assertEquals(dummyMovie.id, data.id)
        assertEquals(dummyMovie.title, data.title)
        assertEquals(dummyMovie.overview, data.overview)
        assertEquals(dummyMovie.poster, data.poster)
        assertEquals(dummyMovie.imgPreview, data.imgPreview)
        assertEquals(dummyMovie.rating.toString(), data.rating.toString())
        assertEquals(dummyMovie.releaseDate, data.releaseDate)
        assertEquals(dummyMovie.isFavorite, data.isFavorite)

        viewModel.getDetailSearchMovie(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getSearchTvShowDetail() {
        val tvShow = MutableLiveData<TvEntity>()
        tvShow.value = dummyTvShow

        Mockito.`when`(catalogRepository.getDetailSearchTvShows(tvShowId)).thenReturn(tvShow)

        val data = viewModel.getDetailSearchTv(tvShowId).value as TvEntity

        assertNotNull(data)
        assertEquals(dummyTvShow.id, data.id)
        assertEquals(dummyTvShow.title, data.title)
        assertEquals(dummyTvShow.overview, data.overview)
        assertEquals(dummyTvShow.poster, data.poster)
        assertEquals(dummyTvShow.imgPreview, data.imgPreview)
        assertEquals(dummyTvShow.rating.toString(), data.rating.toString())
        assertEquals(dummyTvShow.releaseDate, data.releaseDate)
        assertEquals(dummyMovie.isFavorite, data.isFavorite)

        viewModel.getDetailSearchTv(tvShowId).observeForever(observerTv)
        verify(observerTv).onChanged(dummyTvShow)
    }

    @Test
    fun getTrailerMovie() {
        val movieTrailer = MutableLiveData<DataTrailer>()
        movieTrailer.value = dummyMovieTrailer

        Mockito.`when`(catalogRepository.getTrailerMovie(movieId)).thenReturn(movieTrailer)

        val data = viewModel.getTrailerMovie(movieId).value

        assertNotNull(data)
        assertEquals(dummyMovieTrailer.link, data?.link)

        viewModel.getTrailerMovie(movieId).observeForever(observerTrailer)
        verify(observerTrailer).onChanged(dummyMovieTrailer)
    }

    @Test
    fun getTrailerTv() {
        val tvTrailer = MutableLiveData<DataTrailer>()
        tvTrailer.value = dummyTvTrailer

        Mockito.`when`(catalogRepository.getTrailerTvShow(tvShowId)).thenReturn(tvTrailer)

        val data = viewModel.getTrailerTv(tvShowId).value

        assertNotNull(data)
        assertEquals(dummyTvTrailer.link, data?.link)

        viewModel.getTrailerTv(tvShowId).observeForever(observerTrailer)
        verify(observerTrailer).onChanged(dummyTvTrailer)
    }

    @Test
    fun setFavoriteMovie() {
        val movieDummy = MutableLiveData<MovieEntity>()
        movieDummy.value = dummyMovie

        viewModel.setFavoriteMovie(movieDummy.value!!, true)
        verify(catalogRepository).setFavMovie(movieDummy.value!!, true)
        verifyNoMoreInteractions(movieObserver)
    }

    @Test
    fun setFavoriteTvShow() {
        val tvDummy = MutableLiveData<TvEntity>()
        tvDummy.value = dummyTvShow

        viewModel.setFavoriteTvShow(tvDummy.value!!, true)
        verify(catalogRepository).setFavTvShow(tvDummy.value!!, true)
        verifyNoMoreInteractions(tvObserver)
    }
}
