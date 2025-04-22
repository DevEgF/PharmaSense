package com.egitof.auth.di

import com.egitof.auth.data.datasource.FirebaseAuthDataSource
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Module
@InstallIn(SingletonComponent::class)
object FirebaseAuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthDataSource(
        firebaseAuth: FirebaseAuth
    ): FirebaseAuthDataSource {
        return FirebaseAuthDataSource(firebaseAuth)
    }
}
