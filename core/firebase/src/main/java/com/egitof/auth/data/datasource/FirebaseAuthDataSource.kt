package com.egitof.auth.data.datasource

import com.egitof.auth.domain.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseAuthDataSource {
    private val auth = Firebase.auth

    suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(email: String, password: String): Result<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
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

    fun logout() {
        auth.signOut()
    }
}
