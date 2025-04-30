package com.egitof.access.registration.data.repository

import com.egitof.access.registration.domain.repository.RegisterRepository
import com.egitof.auth.data.datasource.FirebaseAuthDataSource
import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.ResultStatus
import com.egitof.utils.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseAuthDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): RegisterRepository {

    override suspend fun register(email: String, password: String): ResultStatus<Unit, AuthError> {
        return withContext(ioDispatcher) {
            dataSource.register(email, password)
        }
    }
}