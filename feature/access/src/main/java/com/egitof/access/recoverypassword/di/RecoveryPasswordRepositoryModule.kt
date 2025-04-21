package com.egitof.access.recoverypassword.di

import com.egitof.access.recoverypassword.data.RecoveryPasswordRepositoryImpl
import com.egitof.access.recoverypassword.domain.repository.RecoveryPasswordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RecoveryPasswordRepositoryModule {
    @Binds
    @Singleton
    fun bindsRecoveryPasswordRepository(
        repositoryImpl: RecoveryPasswordRepositoryImpl
    ): RecoveryPasswordRepository
}