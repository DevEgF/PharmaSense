package com.egitof.access.registration.di

import com.egitof.access.registration.data.repository.RegisterRepositoryImpl
import com.egitof.access.registration.domain.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RegisterRepositoryModule {
    @Binds
    @Singleton
    fun bindsRegisterRepository(repository: RegisterRepositoryImpl): RegisterRepository
}