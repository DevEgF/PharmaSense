package com.egitof.auth.domain.model

sealed interface AuthError {
    data object EmptyFields : AuthError
    data object InvalidEmailFormat : AuthError
    data class InvalidCredentials(val message: String? = null) : AuthError
    data class NetworkError(val message: String? = null) : AuthError
    data class GenericError(val message: String? = null) : AuthError
}
