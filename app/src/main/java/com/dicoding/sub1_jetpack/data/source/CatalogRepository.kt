package com.dicoding.sub1_jetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.sub1_jetpack.data.model.DataTrailer
import com.dicoding.sub1_jetpack.data.source.local.LocalDataSource
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import com.dicoding.sub1_jetpack.data.source.local.voLocal.Resource
import com.dicoding.sub1_jetpack.data.source.remote.RemoteCallback
import com.dicoding.sub1_jetpack.data.source.remote.RemoteDataSource
import com.dicoding.sub1_jetpack.data.source.remote.response.MovieResponse
import com.dicoding.sub1_jetpack.data.source.remote.response.TrailerResponse
import com.dicoding.sub1_jetpack.data.source.remote.response.TvResponse
import com.dicoding.sub1_jetpack.data.source.remote.voRemote.ApiResponse
import com.dicoding.sub1_jetpack.utils.Helper.posterLink
import com.dicoding.sub1_jetpack.utils.Helper.previewLink
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CatalogDataSource {
    override fun getSearchMovies(query: String): LiveData<List<MovieEntity>> {
        val searchList = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getSearchMovies(query, object : RemoteCallback.LoadSearchMovieCallback {
            override fun onSearchMovieReceived(searchMovieResponse: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (i in searchMovieResponse) {
                    val movie = MovieEntity(
                        i.id,
                        i.title,
                        i.overview,
                        posterLink + i.poster,
                        previewLink + i.imgPreview,
                        i.rating,
                        i.releaseDate,
                        false
                    )
                    movieList.add(movie)
                }
                searchList.postValue(movieList)
            }
        })
        return searchList
    }

    override fun getListMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>() {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getListMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getTopRatedMovies()

            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (i in data) {
                    val movie = MovieEntity(
                        i.id,
                        i.title,
                        i.overview,
                        posterLink + i.poster,
                        previewLink + i.imgPreview,
                        i.rating,
                        i.releaseDate,
                        false
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<MovieEntity> =
        localDataSource.getDetailMovie(movieId)

    override fun getDetailSearchMovie(movieId: Int): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getDetailSearchMovies(
            movieId,
            object : RemoteCallback.LoadDetailSearchMovieCallback {
                override fun onDetailSearchMovieReceived(movieResponse: MovieResponse) {
                    val movie = MovieEntity(
                        movieResponse.id,
                        movieResponse.title,
                        movieResponse.overview,
                        posterLink + movieResponse.poster,
                        previewLink + movieResponse.imgPreview,
                        movieResponse.rating,
                        movieResponse.releaseDate,
                        false
                    )
                    movieResult.postValue(movie)
                }
            })
        return movieResult
    }

    override fun getTrailerMovie(movieId: Int): LiveData<DataTrailer> {
        val movieTrailer = MutableLiveData<DataTrailer>()
        remoteDataSource.getTrailerMovie(movieId, object : RemoteCallback.LoadTrailerMovieCallback {
            var trailer = DataTrailer()
            override fun onTrailerMovieReceived(movieTrailerResponse: List<TrailerResponse>) {
                if (movieTrailerResponse.isNotEmpty())
                    trailer = DataTrailer(movieTrailerResponse[0].link.toString())
                movieTrailer.postValue(trailer)
            }
        })
        return movieTrailer
    }

    override fun getListFavMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getListFavMovies(), config).build()
    }

    override fun setFavMovie(movie: MovieEntity, state: Boolean) {
        CoroutineScope(IO).launch {
            localDataSource.setFavMovie(movie, state)
        }
    }

    override fun getSearchTvShows(query: String): LiveData<List<TvEntity>> {
        val searchList = MutableLiveData<List<TvEntity>>()
        remoteDataSource.getSearchTvShows(query, object : RemoteCallback.LoadSearchTvCallback {
            override fun onSearchTvReceived(searchTvResponse: List<TvResponse>) {
                val tvList = ArrayList<TvEntity>()
                for (i in searchTvResponse) {
                    val movie = TvEntity(
                        i.id,
                        i.title,
                        i.overview,
                        posterLink + i.poster,
                        previewLink + i.imgPreview,
                        i.rating,
                        i.releaseDate,
                        false
                    )
                    tvList.add(movie)
                }
                searchList.postValue(tvList)
            }
        })
        return searchList
    }

    override fun getListTvShows(sort: String): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, List<TvResponse>>() {
            public override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getListTvShows(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean =
                data == null || data.isEmpty()


            public override fun createCall(): LiveData<ApiResponse<List<TvResponse>>> =
                remoteDataSource.getTopRatedTvShows()

            public override fun saveCallResult(data: List<TvResponse>) {
                val tvList = ArrayList<TvEntity>()
                for (i in data) {
                    val tv = TvEntity(
                        i.id,
                        i.title,
                        i.overview,
                        posterLink + i.poster,
                        previewLink + i.imgPreview,
                        i.rating,
                        i.releaseDate,
                        false
                    )
                    tvList.add(tv)
                }

                localDataSource.insertTvShows(tvList)
            }

        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<TvEntity> =
        localDataSource.getDetailTvShow(tvShowId)

    override fun getDetailSearchTvShows(tvShowId: Int): LiveData<TvEntity> {
        val tvResult = MutableLiveData<TvEntity>()
        remoteDataSource.getDetailSearchTvShow(
            tvShowId,
            object : RemoteCallback.LoadDetailSearchTvCallback {
                override fun onDetailSearchTvReceived(tvResponse: TvResponse) {
                    val tv = TvEntity(
                        tvResponse.id,
                        tvResponse.title,
                        tvResponse.overview,
                        posterLink + tvResponse.poster,
                        previewLink + tvResponse.imgPreview,
                        tvResponse.rating,
                        tvResponse.releaseDate,
                        false
                    )
                    tvResult.postValue(tv)
                }
            })
        return tvResult
    }

    override fun getTrailerTvShow(tvId: Int): LiveData<DataTrailer> {
        val tvTrailer = MutableLiveData<DataTrailer>()
        remoteDataSource.getTrailerTvShow(tvId, object : RemoteCallback.LoadTrailerTvCallback {
            var trailer = DataTrailer()
            override fun onTrailerTvReceived(tvTrailerResponse: List<TrailerResponse>) {
                if (tvTrailerResponse.isNotEmpty())
                    trailer = DataTrailer(tvTrailerResponse[0].link.toString())
                tvTrailer.postValue(trailer)
            }
        })
        return tvTrailer
    }

    override fun getListFavTvShows(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getListFavTvShows(), config).build()
    }

    override fun setFavTvShow(tvShow: TvEntity, state: Boolean) {
        CoroutineScope(IO).launch {
            localDataSource.setFavTvShow(tvShow, state)
        }
    }
}
