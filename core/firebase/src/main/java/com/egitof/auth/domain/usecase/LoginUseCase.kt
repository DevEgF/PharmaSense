package com.egitof.auth.domain.usecase

import com.egitof.auth.domain.model.AuthError
import com.egitof.auth.domain.repository.AuthRepository
import com.egitof.utils.Resource
import com.egitof.utils.onFailure
import com.egitof.utils.onSuccess
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<Unit, AuthError> {
        when {
            email.isBlank() -> return Resource.Error(AuthError.EmptyEmail)
            !isValidEmail(email) -> return Resource.Error(AuthError.InvalidEmailFormat)
            password.isBlank() -> return Resource.Error(AuthError.EmptyPassword)
        }

        return authRepository.login(email, password)
    }

    private fun isValidEmail(email: String): Boolean {
        return EMAIL_REGEX.matches(email)
    }

    private companion object {
        val EMAIL_REGEX = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
    }
}