package com.egitof.chat.data.datasource

interface AIChatRemoteDataSource {
    suspend fun sendPrompt(question: String): String?
}