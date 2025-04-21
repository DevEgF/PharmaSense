package com.egitof.access.user.data

import com.egitof.access.user.domain.repository.CurrentUserRepository
import com.egitof.auth.data.datasource.FirebaseAuthDataSource
import com.egitof.auth.domain.model.User
import com.egitof.utils.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CurrentUserRepositoryImpl(
    private val dataSource: FirebaseAuthDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): CurrentUserRepository{

    override suspend fun getCurrentUser(): User? {
        return withContext(ioDispatcher) {
            dataSource.getCurrentUser()
        }
    }
}