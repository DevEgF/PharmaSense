package com.egitof.auth.di

import com.egitof.auth.data.datasource.FirebaseAuthDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthDataSource(): FirebaseAuthDataSource {
        return FirebaseAuthDataSource()
    }
}
