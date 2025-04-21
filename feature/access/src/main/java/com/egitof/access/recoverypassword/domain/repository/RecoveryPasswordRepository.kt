package com.egitof.access.recoverypassword.domain.repository

import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.Resource

interface RecoveryPasswordRepository {
    suspend fun sendPasswordResetEmail(email: String): Resource<Unit, AuthError>
}