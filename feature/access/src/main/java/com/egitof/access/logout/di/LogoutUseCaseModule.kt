package com.egitof.access.logout.di

import com.egitof.access.logout.domain.repository.LogoutRepository
import com.egitof.access.logout.domain.usecase.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object LogoutUseCaseModule {
    @Provides
    fun providesLogoutUseCase(repository: LogoutRepository): LogoutUseCase {
        return LogoutUseCase(repository)
    }
}