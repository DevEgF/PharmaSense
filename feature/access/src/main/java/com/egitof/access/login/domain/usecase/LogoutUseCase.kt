package com.egitof.access.login.domain.usecase

import com.egitof.access.login.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}