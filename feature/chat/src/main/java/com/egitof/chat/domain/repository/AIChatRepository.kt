package com.egitof.chat.domain.repository

import com.egitof.chat.domain.model.AIChatText
import com.egitof.chat.domain.model.ChatError
import com.egitof.utils.data.ResultStatus

interface AIChatRepository {
    suspend fun sendUserQuestion(question: String): ResultStatus<Unit, ChatError>
    suspend fun getChatHistory(): ResultStatus<List<AIChatText>, ChatError>
}