package com.egitof.chat.data.datasource.remote

interface AIChatRemoteDataSource {
    suspend fun sendPrompt(question: String): String?
}