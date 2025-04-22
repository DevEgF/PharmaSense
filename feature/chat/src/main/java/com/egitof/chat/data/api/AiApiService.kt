package com.egitof.chat.data.api

interface AiApiService {
    suspend fun sendPrompt(question: String): String?
}