package com.dicoding.sub1_jetpack.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.sub1_jetpack.data.source.local.LocalDataSource
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import com.dicoding.sub1_jetpack.data.source.local.voLocal.Resource
import com.dicoding.sub1_jetpack.data.source.remote.RemoteCallback
import com.dicoding.sub1_jetpack.data.source.remote.RemoteDataSource
import com.dicoding.sub1_jetpack.utils.DataDummyModel
import com.dicoding.sub1_jetpack.utils.DataDummyResponse
import com.dicoding.sub1_jetpack.utils.LiveDataTestUtil
import com.dicoding.sub1_jetpack.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(remote, local)

    private val listMovieResponse = DataDummyResponse.generateDummyMovies()
    private val listTvShowResponse = DataDummyResponse.generateDummyShows()
    private val listMovie = DataDummyModel.generateDummyMovies()
    private val listTvShow = DataDummyModel.generateDummyShows()
    private val listTrailerMovie = DataDummyResponse.generateDummyMovieTrailer()
    private val listTrailerTvShow = DataDummyResponse.generateDummyTvShowTrailer()
    private val movie = listMovie[1]
    private val tvShow = listTvShow[1]
    private val movieResponse = listMovieResponse[1]
    private val tvShowResponse = listTvShowResponse[1]
    private val movieTrailer = listTrailerMovie[0]
    private val tvShowTrailer = listTrailerTvShow[0]

    @Test
    fun getSearchMovie() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteCallback.LoadSearchMovieCallback).onSearchMovieReceived(
                    listMovieResponse
                )
                null
            }.`when`(remote).getSearchMovies(eq("The Godfather"), any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getSearchMovies("The Godfather"))

        runBlocking {
            verify(remote).getSearchMovies(eq("The Godfather"), any())
        }

        assertNotNull(data)
        assertEquals(listMovie.size, data.size)

    }

    @Test
    fun getSearchTvShow() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteCallback.LoadSearchTvCallback).onSearchTvReceived(
                    listTvShowResponse
                )
                null
            }.`when`(remote).getSearchTvShows(eq("Snowfall"), any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getSearchTvShows("Snowfall"))

        runBlocking {
            verify(remote).getSearchTvShows(eq("Snowfall"), any())
        }

        assertNotNull(data)
        assertEquals(listTvShow.size, data.size)
    }

    @Test
    fun getTopRatedMovies() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getListMovies("BEST")).thenReturn(dataSourceFactory)
        catalogRepository.getListMovies("BEST")

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummyModel.generateDummyMovies()))
        verify(local).getListMovies("BEST")
        assertNotNull(movieEntities)
        assertEquals(listMovie.size, movieEntities.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movie
        Mockito.`when`(local.getDetailMovie(movie.id)).thenReturn(dummyMovie)

        val data = LiveDataTestUtil.getValue(catalogRepository.getDetailMovie(movie.id))
        Mockito.verify(local).getDetailMovie(movie.id)
        assertNotNull(data)
        assertEquals(movie.id, data.id)
    }

    @Test
    fun getDetailSearchMovie() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteCallback.LoadDetailSearchMovieCallback).onDetailSearchMovieReceived(
                    movieResponse
                )
                null
            }.`when`(remote).getDetailSearchMovies(eq(movie.id), any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getDetailSearchMovie(movie.id))

        runBlocking {
            verify(remote).getDetailSearchMovies(eq(movie.id), any())
        }

        assertNotNull(data)
        assertEquals(movie.id, data.id)
    }

    @Test
    fun getTopRatedTvShow() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(local.getListTvShows("BEST")).thenReturn(dataSourceFactory)
        catalogRepository.getListTvShows("BEST")

        val tvEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummyModel.generateDummyShows()))
        verify(local).getListTvShows("BEST")
        assertNotNull(tvEntities)
        assertEquals(listMovie.size, tvEntities.data?.size)
    }

    @Test
    fun getDetailTvShow() {
        val dummyTv = MutableLiveData<TvEntity>()
        dummyTv.value = tvShow
        Mockito.`when`(local.getDetailTvShow(tvShow.id)).thenReturn(dummyTv)

        val data = LiveDataTestUtil.getValue(catalogRepository.getDetailTvShow(tvShow.id))
        Mockito.verify(local).getDetailTvShow(tvShow.id)
        assertNotNull(data)
        assertEquals(tvShow.id, data.id)
    }

    @Test
    fun getDetailSearchTvShow() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteCallback.LoadDetailSearchTvCallback).onDetailSearchTvReceived(
                    tvShowResponse
                )
                null
            }.`when`(remote).getDetailSearchTvShow(eq(tvShow.id), any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getDetailSearchTvShows(tvShow.id))

        runBlocking {
            verify(remote).getDetailSearchTvShow(eq(tvShow.id), any())
        }

        assertNotNull(data)
        assertEquals(tvShow.id, data.id)
    }

    @Test
    fun getTrailerMovie() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteCallback.LoadTrailerMovieCallback).onTrailerMovieReceived(
                    listTrailerMovie
                )
                null
            }.`when`(remote).getTrailerMovie(eq(movie.id), any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getTrailerMovie(movie.id))

        runBlocking {
            verify(remote).getTrailerMovie(eq(movie.id), any())
        }

        assertNotNull(data)
        assertEquals(movieTrailer.link, data.link)
    }

    @Test
    fun getTrailerTv() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteCallback.LoadTrailerTvCallback).onTrailerTvReceived(
                    listTrailerTvShow
                )
                null
            }.`when`(remote).getTrailerTvShow(eq(tvShow.id), any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getTrailerTvShow(tvShow.id))

        runBlocking {
            verify(remote).getTrailerTvShow(eq(tvShow.id), any())
        }

        assertNotNull(data)
        assertEquals(tvShowTrailer.link, data.link)
    }

    @Test
    fun getListFavoriteMovies() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getListFavMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getListFavMovies()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummyModel.generateDummyMovies()))
        Mockito.verify(local).getListFavMovies()
        assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getListFavoriteTvShows() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(local.getListFavTvShows()).thenReturn(dataSourceFactory)
        catalogRepository.getListFavTvShows()

        val tvShowEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummyModel.generateDummyMovies()))
        Mockito.verify(local).getListFavTvShows()
        assertNotNull(tvShowEntity.data)
        assertEquals(listTvShow.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun setFavoriteMovie() {
        catalogRepository.setFavMovie(DataDummyModel.getDetailMovie(), true)
        verify(local).setFavMovie(DataDummyModel.getDetailMovie(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun setFavoriteTvShow() {
        catalogRepository.setFavTvShow(DataDummyModel.getDetailTvShow(), true)
        verify(local).setFavTvShow(DataDummyModel.getDetailTvShow(), true)
        verifyNoMoreInteractions(local)
    }
}