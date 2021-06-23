package com.dicoding.sub1_jetpack.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.sub1_jetpack.data.source.CatalogRepository
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import com.dicoding.sub1_jetpack.data.source.local.voLocal.Resource
import com.dicoding.sub1_jetpack.utils.DataDummyModel
import com.nhaarman.mockitokotlin2.verify
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
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    private val listDummyMovie = DataDummyModel.generateDummyMovies()
    private val listDummyTvShow = DataDummyModel.generateDummyShows()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observerMovie: Observer<List<MovieEntity>>

    @Mock
    private lateinit var observerTv: Observer<List<TvEntity>>

    @Mock
    private lateinit var observerListMovie: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var observerListTv: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Mock
    private lateinit var pagedListTv: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(catalogRepository)
    }

    @Test
    fun getTopRatedMovies() {
        val dummyMovies = Resource.success(pagedListMovie)
        Mockito.`when`(dummyMovies.data?.size).thenReturn(3)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        Mockito.`when`(catalogRepository.getListMovies("BEST")).thenReturn(movies)
        val movie = viewModel.getListMovies("BEST").value?.data
        verify(catalogRepository).getListMovies("BEST")
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        viewModel.getListMovies("BEST").observeForever(observerListMovie)
        verify(observerListMovie).onChanged(dummyMovies)
    }

    @Test
    fun getTopRatedTvShows() {
        val dummyTvShow = Resource.success(pagedListTv)
        Mockito.`when`(dummyTvShow.data?.size).thenReturn(3)
        val tvShows = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(catalogRepository.getListTvShows("BEST")).thenReturn(tvShows)
        val tvShow = viewModel.getListTvShows("BEST").value?.data
        verify(catalogRepository).getListTvShows("BEST")
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getListTvShows("BEST").observeForever(observerListTv)
        verify(observerListTv).onChanged(dummyTvShow)
    }

    @Test
    fun getSearchMovies() {
        val movie = MutableLiveData<List<MovieEntity>>()
        movie.value = listDummyMovie

        Mockito.`when`(catalogRepository.getSearchMovies("The Godfather")).thenReturn(movie)

        val dataSearchMovie = viewModel.getSearchMovies("The Godfather").value
        verify(catalogRepository).getSearchMovies("The Godfather")

        assertNotNull(dataSearchMovie)
        assertEquals(10, dataSearchMovie?.size)

        viewModel.getSearchMovies("The Godfather").observeForever(observerMovie)
        verify(observerMovie).onChanged(listDummyMovie)
    }

    @Test
    fun getSearchTvShows() {
        val tvShow = MutableLiveData<List<TvEntity>>()
        tvShow.value = listDummyTvShow

        Mockito.`when`(catalogRepository.getSearchTvShows("Snowfall")).thenReturn(tvShow)

        val dataListTvShow = viewModel.getSearchTvShows("Snowfall").value
        verify(catalogRepository).getSearchTvShows("Snowfall")

        assertNotNull(dataListTvShow)
        assertEquals(10, dataListTvShow?.size)

        viewModel.getSearchTvShows("Snowfall").observeForever(observerTv)
        verify(observerTv).onChanged(listDummyTvShow)
    }
}
