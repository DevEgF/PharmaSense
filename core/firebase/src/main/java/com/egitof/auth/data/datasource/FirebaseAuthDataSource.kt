package com.egitof.auth.data.datasource

import com.egitof.auth.domain.model.AuthError
import com.egitof.auth.domain.model.User
import com.egitof.utils.Resource
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseAuthDataSource {
    private val auth = Firebase.auth

    suspend fun login(email: String, password: String): Resource<Unit, AuthError> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(handleFirebaseAuthException(e))
        }
    }

    suspend fun register(email: String, password: String): Resource<Unit, AuthError> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(handleFirebaseAuthException(e))
        }
    }

    fun getCurrentUser(): User? {
        return auth.currentUser?.let { firebaseUser ->
            User(
                id = firebaseUser.uid,
                email = firebaseUser.email ?: "",
                isEmailVerified = firebaseUser.isEmailVerified
            )
        }
    }

    private fun handleFirebaseAuthException(exception: Exception): AuthError {
        return when (exception) {
            is FirebaseAuthInvalidCredentialsException -> {
                when (exception.errorCode) {
                    INVALID_EMAIL -> AuthError.InvalidEmail(exception.message)
                    ERROR_WRONG_PASSWORD -> AuthError.InvalidPassword(exception.message)
                    else -> AuthError.InvalidCredentials(exception.message)
                }
            }
            is FirebaseAuthInvalidUserException -> {
                when (exception.errorCode) {
                    ERROR_USER_NOT_FOUND -> AuthError.UserNotFound(exception.message)
                    ERROR_USER_DISABLED -> AuthError.UserDisabled(exception.message)
                    else -> AuthError.UnknownError(exception.message)
                }
            }
            is FirebaseNetworkException -> AuthError.NetworkError(exception.message)
            is FirebaseTooManyRequestsException -> AuthError.ServerError(exception.message)
            else -> AuthError.UnknownError(exception.message)
        }
    }

    fun logout() {
        auth.signOut()
    }

    private companion object {
        const val INVALID_EMAIL = "ERROR_INVALID_EMAIL"
        const val ERROR_WRONG_PASSWORD = "ERROR_WRONG_PASSWORD"
        const val ERROR_USER_NOT_FOUND = "ERROR_USER_NOT_FOUND"
        const val ERROR_USER_DISABLED = "ERROR_USER_DISABLED"
    }
}
