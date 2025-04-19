package com.egitof.utils

sealed class Resource<out T, out E> {
    data class Success<out T>(val data: T) : Resource<T, Nothing>()
    data class Error<out E>(val error: E) : Resource<Nothing, E>()
}

fun <T, E> Resource<T, E>.onSuccess(action: (T) -> Unit): Resource<T, E> {
    if (this is Resource.Success) action(data)
    return this
}

fun <T, E> Resource<T, E>.onFailure(action: (E) -> Unit): Resource<T, E> {
    if (this is Resource.Error) action(error)
    return this
}