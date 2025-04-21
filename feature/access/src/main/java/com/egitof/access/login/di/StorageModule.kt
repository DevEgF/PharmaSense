package com.egitof.access.login.di

import android.content.Context
import android.content.SharedPreferences
import com.egitof.access.login.data.AuthStateManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(
            AuthStateManager.PREF_AUTH_STATE,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideAuthStateManager(
        sharedPreferences: SharedPreferences
    ): AuthStateManager {
        return AuthStateManager(sharedPreferences)
    }
}