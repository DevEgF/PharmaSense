package com.egitof.access.user.di

import android.content.Context
import android.content.SharedPreferences
import com.egitof.access.user.data.CurrentUserStateManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrentUserStorageModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(
            CurrentUserStateManager.Companion.PREF_AUTH_STATE,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideAuthStateManager(
        sharedPreferences: SharedPreferences
    ): CurrentUserStateManager {
        return CurrentUserStateManager(sharedPreferences)
    }
}