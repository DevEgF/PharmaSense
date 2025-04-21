package com.egitof.access.login.data.repository

import com.egitof.access.login.domain.repository.AuthRepository
import com.egitof.auth.data.datasource.FirebaseAuthDataSource
import com.egitof.auth.domain.model.AuthError
import com.egitof.auth.domain.model.User
import com.egitof.utils.data.Resource
import com.egitof.utils.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseAuthDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Resource<Unit, AuthError> {
        return withContext(ioDispatcher) {
            dataSource.login(email, password)
        }
    }

    override suspend fun getCurrentUser(): User? {
        return withContext(ioDispatcher) {
            dataSource.getCurrentUser()
        }
    }
}