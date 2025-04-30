package com.egitof.chat.data.datasource.local

interface AiChatLocalDataSource {
    suspend fun insertAIChatConversation(question: AIChatTextEntity, answer: AIChatTextEntity)
    suspend fun getAIChatHistory(): List<AIChatTextEntity>
}