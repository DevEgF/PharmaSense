package com.egitof.access.logout.di

import com.egitof.access.logout.domain.repository.LogoutRepository
import com.egitof.access.logout.domain.usecase.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LogoutUseCaseModule {
    @Provides
    @Singleton
    fun providesLogoutUseCase(repository: LogoutRepository): LogoutUseCase {
        return LogoutUseCase(repository)
    }
}