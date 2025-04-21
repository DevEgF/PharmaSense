package com.egitof.access.logout.di

import com.egitof.access.logout.data.LogoutRepositoryImpl
import com.egitof.access.logout.domain.repository.LogoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface LogoutRepositoryModule {
    @Binds
    fun bindLogoutRepository(repository: LogoutRepositoryImpl): LogoutRepository
}