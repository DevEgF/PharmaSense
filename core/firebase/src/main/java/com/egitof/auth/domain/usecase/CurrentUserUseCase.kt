package com.egitof.auth.domain.usecase

import com.egitof.auth.domain.model.User
import com.egitof.auth.domain.repository.AuthRepository
import javax.inject.Inject

class CurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): User? {
        return repository.getCurrentUser()
    }
}
