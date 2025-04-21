package com.egitof.access.registration.domain.usecase

import com.egitof.access.registration.domain.repository.RegisterRepository
import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.Resource
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<Unit, AuthError> {
        return repository.register(email, password)
    }
}