package com.egitof.access.login.di

import com.egitof.access.login.data.repository.AuthRepositoryImpl
import com.egitof.access.login.domain.repository.AuthRepository
import com.egitof.access.login.domain.usecase.CurrentUserUseCase
import com.egitof.access.login.domain.usecase.LoginUseCase
import com.egitof.access.login.domain.usecase.LogoutUseCase
import com.egitof.access.login.domain.usecase.RegisterUseCase
import com.egitof.auth.data.datasource.FirebaseAuthDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        dataSource: FirebaseAuthDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCurrentUserUseCase(repository: AuthRepository): CurrentUserUseCase {
        return CurrentUserUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(repository: AuthRepository): LogoutUseCase {
        return LogoutUseCase(repository)
    }
}