package com.egitof.access.login.domain.usecase

import android.util.Patterns
import com.egitof.access.login.domain.repository.AuthRepository
import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<Unit, AuthError> {
        when {
            email.isBlank() -> return Resource.Error(AuthError.EmptyFields)
            !isValidEmail(email) -> return Resource.Error(AuthError.InvalidEmailFormat)
            password.isBlank() -> return Resource.Error(AuthError.EmptyFields)
        }

        return authRepository.login(email, password)
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}