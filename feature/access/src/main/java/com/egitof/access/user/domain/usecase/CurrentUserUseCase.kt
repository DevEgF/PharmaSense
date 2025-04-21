package com.egitof.access.user.domain.usecase

import com.egitof.access.login.domain.repository.LoginRepository
import com.egitof.access.user.domain.repository.CurrentUserRepository
import com.egitof.auth.domain.model.User
import javax.inject.Inject

class CurrentUserUseCase @Inject constructor(
    private val repository: CurrentUserRepository
) {
    suspend operator fun invoke(): User? {
        return repository.getCurrentUser()
    }
}