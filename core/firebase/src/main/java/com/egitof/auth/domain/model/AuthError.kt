package com.egitof.auth.domain.model

sealed class AuthError : Exception() {
    data object EmptyEmail : AuthError()
    data object InvalidEmailFormat : AuthError()
    data object EmptyPassword : AuthError()
    data object PasswordTooShort : AuthError()
    data class InvalidCredentials(override val message: String? = null) : AuthError()
    data class InvalidEmail(override val message: String? = null) : AuthError()
    data class InvalidPassword(override val message: String? = null) : AuthError()
    data class UserNotFound(override val message: String? = null) : AuthError()
    data class UserDisabled(override val message: String? = null) : AuthError()
    data class EmailAlreadyInUse(override val message: String? = null) : AuthError()
    data class WeakPassword(override val message: String? = null) : AuthError()
    data class NetworkError(override val message: String? = null) : AuthError()
    data class ServerError(override val message: String? = null) : AuthError()
    data class UnknownError(override val message: String? = null) : AuthError()
}