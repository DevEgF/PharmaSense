package com.egitof.access.registration.di

import com.egitof.access.registration.data.repository.RegisterRepositoryImpl
import com.egitof.access.registration.domain.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RegisterRepositoryModule {
    @Binds
    fun bindsRegisterRepository(repository: RegisterRepositoryImpl): RegisterRepository
}