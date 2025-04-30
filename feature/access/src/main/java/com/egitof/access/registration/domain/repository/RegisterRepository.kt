package com.egitof.access.registration.domain.repository

import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.ResultStatus

interface RegisterRepository {
    suspend fun register(email: String, password: String): ResultStatus<Unit, AuthError>
}