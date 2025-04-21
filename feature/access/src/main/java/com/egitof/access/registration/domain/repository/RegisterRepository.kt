package com.egitof.access.registration.domain.repository

import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.Resource

interface RegisterRepository {
    suspend fun register(email: String, password: String): Resource<Unit, AuthError>
}