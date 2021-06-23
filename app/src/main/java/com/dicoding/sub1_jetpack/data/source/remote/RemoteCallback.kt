package com.dicoding.sub1_jetpack.data.source.remote

import com.dicoding.sub1_jetpack.data.source.remote.response.MovieResponse
import com.dicoding.sub1_jetpack.data.source.remote.response.TrailerResponse
import com.dicoding.sub1_jetpack.data.source.remote.response.TvResponse

object RemoteCallback {

    interface LoadSearchMovieCallback {
        fun onSearchMovieReceived(searchMovieResponse: List<MovieResponse>)
    }

    interface LoadSearchTvCallback {
        fun onSearchTvReceived(searchTvResponse: List<TvResponse>)
    }

    interface LoadTrailerMovieCallback {
        fun onTrailerMovieReceived(movieTrailerResponse: List<TrailerResponse>)
    }

    interface LoadTrailerTvCallback {
        fun onTrailerTvReceived(tvTrailerResponse: List<TrailerResponse>)
    }

    interface LoadDetailSearchMovieCallback {
        fun onDetailSearchMovieReceived(movieResponse: MovieResponse)
    }

    interface LoadDetailSearchTvCallback {
        fun onDetailSearchTvReceived(tvResponse: TvResponse)
    }
}