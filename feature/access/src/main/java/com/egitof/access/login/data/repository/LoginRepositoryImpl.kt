package com.egitof.access.login.data.repository

import com.egitof.access.login.domain.repository.LoginRepository
import com.egitof.auth.data.datasource.FirebaseAuthDataSource
import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.Resource
import com.egitof.utils.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseAuthDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): LoginRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Resource<Unit, AuthError> {
        return withContext(ioDispatcher) {
            dataSource.login(email, password)
        }
    }
}