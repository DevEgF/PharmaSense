package com.egitof.auth.domain.usecase

import com.egitof.auth.domain.model.AuthError
import com.egitof.auth.domain.repository.AuthRepository
import com.egitof.utils.Resource
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<Unit, AuthError> {
        return repository.register(email, password)
    }
}