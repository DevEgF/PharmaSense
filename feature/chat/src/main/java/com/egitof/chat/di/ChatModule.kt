package com.egitof.chat.di

import com.egitof.chat.data.api.AiApiService
import com.egitof.chat.data.api.AiApiServiceImpl
import com.egitof.chat.data.datasource.remote.AIChatRemoteDataSource
import com.egitof.chat.data.datasource.remote.AiChatRemoteDataSourceImpl
import com.egitof.utils.di.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    @Provides
    @Singleton
    fun provideApiDataSource() = AiApiServiceImpl()

    @Provides
    @Singleton
    fun providesAiChatRemoteDataSource(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        aiApiService: AiApiService,
    ) = AiChatRemoteDataSourceImpl(ioDispatcher, aiApiService)
}