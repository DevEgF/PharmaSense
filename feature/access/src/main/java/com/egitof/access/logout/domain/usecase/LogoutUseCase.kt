package com.egitof.access.logout.domain.usecase

import com.egitof.access.logout.domain.repository.LogoutRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: LogoutRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}