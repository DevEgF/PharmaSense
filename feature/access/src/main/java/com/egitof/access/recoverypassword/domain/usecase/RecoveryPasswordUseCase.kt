package com.egitof.access.recoverypassword.domain.usecase

import android.util.Patterns
import com.egitof.access.recoverypassword.domain.repository.RecoveryPasswordRepository
import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.ResultStatus
import javax.inject.Inject

class RecoveryPasswordUseCase @Inject constructor(
    private val repository: RecoveryPasswordRepository
) {
    suspend operator fun invoke(email: String): ResultStatus<Unit, AuthError> {
        return when {
            email.isBlank() -> return ResultStatus.Error(AuthError.EmptyFields)
            !isValidEmail(email) -> return ResultStatus.Error(AuthError.InvalidEmailFormat)
            else -> repository.sendPasswordResetEmail(email)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
