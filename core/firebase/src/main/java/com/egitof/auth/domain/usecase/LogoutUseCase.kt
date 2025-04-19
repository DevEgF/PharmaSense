package com.egitof.auth.domain.usecase

import com.egitof.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}