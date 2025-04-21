package com.egitof.access.login.di

import com.egitof.access.login.domain.repository.LoginRepository
import com.egitof.access.login.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object LoginUseCasesModule {

    @Provides
    fun providesLoginUseCase(repository: LoginRepository): LoginUseCase {
        return LoginUseCase(repository)
    }
}