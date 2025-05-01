package com.egitof.chat.domain.usecase

import com.egitof.chat.domain.model.AIChatText
import com.egitof.chat.domain.model.ChatError
import com.egitof.chat.domain.repository.AIChatRepository
import com.egitof.utils.data.ResultStatus

class GetChatHistoryUseCase(
    private val chatRepository: AIChatRepository,
) {
    suspend operator fun invoke(): ResultStatus<List<AIChatText>, ChatError> {
        return try {
            val result = chatRepository.getChatHistory()
            when (result) {
                is ResultStatus.Success -> ResultStatus.Success(result.data)
                is ResultStatus.Error -> ResultStatus.Error(result.error)
            }
        } catch (e: Exception) {
            ResultStatus.Error(
                ChatError.UnknownError(
                    e.message ?: "Failed to retrieve chat history"
                )
            )
        }
    }
}