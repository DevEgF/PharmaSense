package com.egitof.access.logout.di

import com.egitof.access.logout.data.LogoutRepositoryImpl
import com.egitof.access.logout.domain.repository.LogoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LogoutRepositoryModule {
    @Binds
    @Singleton
    fun bindLogoutRepository(repository: LogoutRepositoryImpl): LogoutRepository
}