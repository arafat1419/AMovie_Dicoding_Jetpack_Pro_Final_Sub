package com.dicoding.sub1_jetpack.data.source.remote.voRemote

data class ApiResponse<T>(val status: StatusResponse, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): ApiResponse<T> =
            ApiResponse(StatusResponse.ERROR, data, msg)
    }
}