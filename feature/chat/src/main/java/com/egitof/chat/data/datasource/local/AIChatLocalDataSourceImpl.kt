package com.egitof.chat.data.datasource.local

import com.egitof.utils.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AIChatLocalDataSourceImpl(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val aiChatHistoryDao: AIChatHistoryDao,
): AiChatLocalDataSource {

    override suspend fun insertAIChatConversation(
        question: AIChatTextEntity,
        answer: AIChatTextEntity
    ) {
        withContext(ioDispatcher) {
            aiChatHistoryDao.insertAll(question, answer)
        }
    }

    override suspend fun getAIChatHistory(): List<AIChatTextEntity> {
        return withContext(ioDispatcher) {
            aiChatHistoryDao.getAll().sortedByDescending { it.datetime }
        }
    }
}