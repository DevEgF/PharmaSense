package com.egitof.access.login.domain.repository

import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.Resource

interface LoginRepository {
    suspend fun login(email: String, password: String): Resource<Unit, AuthError>
}