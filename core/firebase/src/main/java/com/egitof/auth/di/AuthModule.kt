package com.egitof.auth.di

import com.egitof.auth.data.AuthRepositoryImpl
import com.egitof.auth.data.datasource.FirebaseAuthDataSource
import com.egitof.auth.domain.repository.AuthRepository
import com.egitof.auth.domain.usecase.CurrentUserUseCase
import com.egitof.auth.domain.usecase.LoginUseCase
import com.egitof.auth.domain.usecase.LogoutUseCase
import com.egitof.auth.domain.usecase.RegisterUseCase
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
    fun provideFirebaseAuthDataSource(): FirebaseAuthDataSource {
        return FirebaseAuthDataSource()
    }

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
