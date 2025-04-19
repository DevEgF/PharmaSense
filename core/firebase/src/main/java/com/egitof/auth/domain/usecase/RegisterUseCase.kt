package com.egitof.auth.domain.usecase

import com.egitof.auth.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.register(email, password)
    }
}