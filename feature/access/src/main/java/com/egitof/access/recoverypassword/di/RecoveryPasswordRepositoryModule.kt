package com.egitof.access.recoverypassword.di

import com.egitof.access.recoverypassword.data.RecoveryPasswordRepositoryImpl
import com.egitof.access.recoverypassword.domain.repository.RecoveryPasswordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RecoveryPasswordRepositoryModule {
    @Binds
    fun bindsRecoveryPasswordRepository(
        repositoryImpl: RecoveryPasswordRepositoryImpl
    ): RecoveryPasswordRepository
}