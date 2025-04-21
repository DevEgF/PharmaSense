package com.egitof.access.user.domain.repository

import com.egitof.auth.domain.model.User

interface CurrentUserRepository {
    suspend fun getCurrentUser(): User?
}