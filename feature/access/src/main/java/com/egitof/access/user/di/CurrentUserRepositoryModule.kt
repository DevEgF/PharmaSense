package com.egitof.access.user.di

import com.egitof.access.user.data.CurrentUserRepositoryImpl
import com.egitof.access.user.domain.repository.CurrentUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CurrentUserRepositoryModule {
    @Binds
    @Singleton
    fun bindsCurrentUserRepository(repositoryImpl: CurrentUserRepositoryImpl): CurrentUserRepository
}