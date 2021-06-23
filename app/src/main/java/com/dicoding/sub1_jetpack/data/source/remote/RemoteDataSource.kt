package com.dicoding.sub1_jetpack.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.sub1_jetpack.data.source.remote.api.ApiService
import com.dicoding.sub1_jetpack.data.source.remote.response.MovieResponse
import com.dicoding.sub1_jetpack.data.source.remote.response.TvResponse
import com.dicoding.sub1_jetpack.data.source.remote.voRemote.ApiResponse
import com.dicoding.sub1_jetpack.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getSearchMovies(query: String, callback: RemoteCallback.LoadSearchMovieCallback) {
        EspressoIdlingResource.increment()
        CoroutineScope(IO).launch {
            try {
                apiService.getSearchMovie(query).await().result?.let {
                    callback.onSearchMovieReceived(it)
                    EspressoIdlingResource.decrement()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun getSearchTvShows(query: String, callback: RemoteCallback.LoadSearchTvCallback) {
        EspressoIdlingResource.increment()
        CoroutineScope(IO).launch {
            try {
                apiService.getSearchTvShow(query).await().result?.let {
                    callback.onSearchTvReceived(it)
                    EspressoIdlingResource.decrement()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun getTopRatedMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        //EspressoIdlingResource.increment()
        val resultTopMovie = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getTopRatedMovies().await()
                Log.d("lihat remote", response.toString())
                resultTopMovie.postValue(ApiResponse.success(response.result))
            } catch (e: IOException) {
                e.printStackTrace()
                resultTopMovie.postValue(
                    ApiResponse.error(e.message, mutableListOf())
                )
                Log.d("lihat remote", e.message.toString())
            }
        }
        //EspressoIdlingResource.decrement()
        return resultTopMovie
    }

    fun getTopRatedTvShows(): LiveData<ApiResponse<List<TvResponse>>> {
        EspressoIdlingResource.increment()
        val resultTopTv = MutableLiveData<ApiResponse<List<TvResponse>>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getTopRatedTvShow().await()
                resultTopTv.postValue(ApiResponse.success(response.result))
            } catch (e: IOException) {
                e.printStackTrace()
                resultTopTv.postValue(
                    ApiResponse.error(e.message, mutableListOf())
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultTopTv
    }

    fun getTrailerMovie(movieId: Int, callback: RemoteCallback.LoadTrailerMovieCallback) {
        EspressoIdlingResource.increment()
        CoroutineScope(IO).launch {
            try {
                apiService.getTrailerMovie(movieId).await().result?.let {
                    callback.onTrailerMovieReceived(it)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
    }

    fun getTrailerTvShow(tvShowId: Int, callback: RemoteCallback.LoadTrailerTvCallback) {
        EspressoIdlingResource.increment()
        CoroutineScope(IO).launch {
            try {
                apiService.getTrailerTv(tvShowId).await().result?.let {
                    callback.onTrailerTvReceived(it)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        EspressoIdlingResource.decrement()
    }

    fun getDetailSearchMovies(
        movieId: Int,
        callback: RemoteCallback.LoadDetailSearchMovieCallback
    ) {
        EspressoIdlingResource.increment()
        CoroutineScope(IO).launch {
            try {
                apiService.getDetailSearchMovie(movieId).await().let {
                    callback.onDetailSearchMovieReceived(it)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
    }

    fun getDetailSearchTvShow(tvShowId: Int, callback: RemoteCallback.LoadDetailSearchTvCallback) {
        EspressoIdlingResource.increment()
        CoroutineScope(IO).launch {
            try {
                apiService.getDetailSearchTv(tvShowId).await().let {
                    callback.onDetailSearchTvReceived(it)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
    }
}