package com.egitof.access.login.di

import com.egitof.access.login.domain.repository.AuthRepository
import com.egitof.access.login.domain.usecase.CurrentUserUseCase
import com.egitof.access.login.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthUseCasesModule {

    @Provides
    @Singleton
    fun providesLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesCurrentUserUseCase(repository: AuthRepository): CurrentUserUseCase {
        return CurrentUserUseCase(repository)
    }
}