package com.egitof.access.user.di

import com.egitof.access.user.domain.repository.CurrentUserRepository
import com.egitof.access.user.domain.usecase.CurrentUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CurrentUserUseCaseModule {

    @Provides
    fun provideCurrentUserUseCase(
        currentUserRepository: CurrentUserRepository
    ): CurrentUserUseCase {
        return CurrentUserUseCase(currentUserRepository)
    }
}