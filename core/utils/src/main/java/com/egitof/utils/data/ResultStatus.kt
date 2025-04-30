package com.egitof.utils.data

sealed class ResultStatus<out T, out E> {
    data class Success<out T>(val data: T) : ResultStatus<T, Nothing>()
    data class Error<out E>(val error: E) : ResultStatus<Nothing, E>()
}