package com.egitof.auth.data

import com.egitof.auth.data.datasource.FirebaseAuthDataSource
import com.egitof.auth.domain.model.AuthError
import com.egitof.auth.domain.model.User
import com.egitof.auth.domain.repository.AuthRepository
import com.egitof.utils.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseAuthDataSource
): AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Resource<Unit, AuthError> {
        return dataSource.login(email, password)
    }

    override suspend fun register(email: String, password: String): Resource<Unit, AuthError> {
        return dataSource.register(email, password)
    }

    override suspend fun getCurrentUser(): User? {
        return dataSource.getCurrentUser()
    }

    override fun logout() {
        dataSource.logout()
    }
}