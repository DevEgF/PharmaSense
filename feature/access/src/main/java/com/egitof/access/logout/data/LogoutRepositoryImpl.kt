package com.egitof.access.logout.data

import com.egitof.access.logout.domain.repository.LogoutRepository
import com.egitof.auth.data.datasource.FirebaseAuthDataSource

class LogoutRepositoryImpl(
    private val dataSource: FirebaseAuthDataSource,
): LogoutRepository {

    override fun logout() {
        dataSource.logout()
    }

}