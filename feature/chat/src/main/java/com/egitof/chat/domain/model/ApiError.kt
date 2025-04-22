package com.egitof.chat.domain.model

sealed class ApiError {
    data class NetworkError(val throwable: Throwable) : ApiError()
    data class ServerError(val code: Int, val message: String?) : ApiError()
    data class Unauthorized(val message: String) : ApiError()
    data class BadRequest(val message: String) : ApiError()
    data class NotFound(val message: String) : ApiError()
    data class UnknownError(val throwable: Throwable) : ApiError()
    object EmptyResponse : ApiError()
}