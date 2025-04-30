package com.egitof.chat.data.datasource.remote

import com.egitof.chat.data.api.AiApiService
import com.egitof.utils.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AiChatRemoteDataSourceImpl @Inject constructor (
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val aiApiService: AiApiService,
): AIChatRemoteDataSource {

    override suspend fun sendPrompt(question: String): String? {
        return withContext(ioDispatcher) {
            aiApiService.sendPrompt(question)
        }
    }
}