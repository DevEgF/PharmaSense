package com.egitof.auth.domain.repository

import com.egitof.auth.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun register(email: String, password: String): Result<Unit>
    suspend fun getCurrentUser(): User?
    fun logout()
}