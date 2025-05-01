package com.egitof.chat.domain.usecase

import com.egitof.chat.domain.model.ChatError
import com.egitof.chat.domain.repository.AIChatRepository
import com.egitof.utils.data.ResultStatus

class SendUserQuestionUseCase(
    private val chatRepository: AIChatRepository,
) {
    suspend operator fun invoke(question: String): ResultStatus<Unit, ChatError> {
        if (question.isBlank()) {
            return ResultStatus.Error(ChatError.EmptyQuestionError)
        }

        val result = chatRepository.sendUserQuestion(question)
        return when (result) {
            is ResultStatus.Error -> ResultStatus.Error(result.error)
            is ResultStatus.Success -> ResultStatus.Success(result.data)
        }
    }
}
