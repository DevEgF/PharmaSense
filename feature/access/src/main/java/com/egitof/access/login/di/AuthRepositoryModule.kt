package com.egitof.access.login.di

import com.egitof.access.login.data.repository.AuthRepositoryImpl
import com.egitof.access.login.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AuthRepositoryModule {
    @Binds
    fun bindsAuthRepository(repository: AuthRepositoryImpl): AuthRepository
}