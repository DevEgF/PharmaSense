package com.egitof.access.recoverypassword.data

import com.egitof.access.recoverypassword.domain.repository.RecoveryPasswordRepository
import com.egitof.auth.data.datasource.FirebaseAuthDataSource
import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.Resource
import com.egitof.utils.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecoveryPasswordRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseAuthDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): RecoveryPasswordRepository {

    override suspend fun sendPasswordResetEmail(email: String): Resource<Unit, AuthError> {
        return withContext(ioDispatcher) {
            dataSource.sendPasswordResetEmail(email)
        }
    }
}