package com.egitof.access.registration.di

import com.egitof.access.registration.domain.repository.RegisterRepository
import com.egitof.access.registration.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegisterUseCaseModule {
    @Provides
    fun providesRegisterUseCase(repository: RegisterRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }
}