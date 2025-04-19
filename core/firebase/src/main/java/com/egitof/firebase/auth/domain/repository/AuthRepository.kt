package com.egitof.firebase.auth.domain.repository

import com.egitof.firebase.auth.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun getCurrentUser(): User?
    fun logout()
}