package com.egitof.access.recoverypassword.di

import com.egitof.access.recoverypassword.domain.repository.RecoveryPasswordRepository
import com.egitof.access.recoverypassword.domain.usecase.RecoveryPasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RecoveryPasswordUseCaseModule {

    @Provides
    fun providesRecoveryPasswordUseCase(
        repository: RecoveryPasswordRepository
    ): RecoveryPasswordUseCase {
        return RecoveryPasswordUseCase(repository)
    }
}