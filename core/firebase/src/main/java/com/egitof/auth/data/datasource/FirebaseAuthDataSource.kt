package com.egitof.auth.data.datasource

import com.egitof.auth.domain.model.AuthError
import com.egitof.auth.domain.model.User
import com.egitof.utils.data.ResultStatus
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthDataSource @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend fun login(email: String, password: String): ResultStatus<Unit, AuthError> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            ResultStatus.Success(Unit)
        } catch (exception: Exception) {
            ResultStatus.Error(handleFirebaseAuthException(exception))
        }
    }

    suspend fun register(email: String, password: String): ResultStatus<Unit, AuthError> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            ResultStatus.Success(Unit)
        } catch (exception: Exception) {
            ResultStatus.Error(handleFirebaseAuthException(exception))
        }
    }

    suspend fun sendPasswordResetEmail(email: String): ResultStatus<Unit, AuthError> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            ResultStatus.Success(Unit)
        } catch (exception: Exception) {
            ResultStatus.Error(handleFirebaseAuthException(exception))
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
            is FirebaseAuthInvalidCredentialsException -> AuthError.InvalidCredentials(exception.message)
            is FirebaseAuthInvalidUserException -> AuthError.InvalidCredentials(exception.message)
            is FirebaseNetworkException -> AuthError.NetworkError(exception.message)
            else -> AuthError.GenericError(exception.message)
        }
    }

    fun logout() {
        auth.signOut()
    }
}
