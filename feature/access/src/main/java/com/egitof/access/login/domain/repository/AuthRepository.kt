package com.egitof.access.login.domain.repository

import com.egitof.auth.domain.model.AuthError
import com.egitof.auth.domain.model.User
import com.egitof.utils.data.Resource

interface AuthRepository {
    suspend fun login(email: String, password: String): Resource<Unit, AuthError>
    suspend fun getCurrentUser(): User?
}