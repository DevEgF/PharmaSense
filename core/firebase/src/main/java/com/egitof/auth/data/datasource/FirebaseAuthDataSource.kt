package com.egitof.auth.data.datasource

import com.egitof.auth.domain.model.AuthError
import com.egitof.auth.domain.model.User
import com.egitof.utils.Resource
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
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
            is FirebaseAuthInvalidCredentialsException -> AuthError.InvalidCredentials(exception.message)
            is FirebaseNetworkException -> AuthError.NetworkError(exception.message)
            else -> AuthError.GenericError(exception.message)
        }
    }

    fun logout() {
        auth.signOut()
    }

}
