package com.egitof.access.login.domain.usecase

import android.util.Patterns
import com.egitof.access.login.domain.repository.LoginRepository
import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.ResultStatus
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String): ResultStatus<Unit, AuthError> {
        when {
            email.isBlank() -> return ResultStatus.Error(AuthError.EmptyFields)
            !isValidEmail(email) -> return ResultStatus.Error(AuthError.InvalidEmailFormat)
            password.isBlank() -> return ResultStatus.Error(AuthError.EmptyFields)
        }

        return loginRepository.login(email, password)
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}