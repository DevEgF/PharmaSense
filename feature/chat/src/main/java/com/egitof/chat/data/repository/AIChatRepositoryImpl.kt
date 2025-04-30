package com.egitof.chat.data.repository

import android.database.sqlite.SQLiteException
import com.egitof.chat.data.datasource.local.AIChatTextEntity
import com.egitof.chat.data.datasource.local.AiChatLocalDataSource
import com.egitof.chat.data.datasource.remote.AIChatRemoteDataSource
import com.egitof.chat.data.mapper.toDomain
import com.egitof.chat.domain.model.AIChatText
import com.egitof.chat.domain.model.AIChatTextType
import com.egitof.chat.domain.model.ChatError
import com.egitof.chat.domain.repository.AIChatRepository
import com.egitof.utils.data.ResultStatus
import kotlinx.io.IOException

class AIChatRepositoryImpl(
    private val aiChatLocalDataSource: AiChatLocalDataSource,
    private val aiChatRemoteDataSource: AIChatRemoteDataSource
): AIChatRepository {

    override suspend fun sendUserQuestion(question: String): ResultStatus<Unit, ChatError> {
        return try {
            val answer = aiChatRemoteDataSource.sendPrompt(question)
                ?: return ResultStatus.Error(ChatError.NetworkError)

            try {
                aiChatLocalDataSource.insertAIChatConversation(
                    question = createUserQuestionEntity(question),
                    answer = createAIAnswerEntity(answer)
                )
                ResultStatus.Success(Unit)
            } catch (_: Exception) {
                ResultStatus.Error(ChatError.DatabaseError)
            }
        } catch (e: Exception) {
            ResultStatus.Error(ChatError.UnknownError(e.message ?: "Unknown error"))
        }
    }

    override suspend fun getChatHistory(): ResultStatus<List<AIChatText>, ChatError> {
        return try {
            val history = aiChatLocalDataSource.getAIChatHistory().toDomain()
            ResultStatus.Success(history)
        } catch (e: Exception) {
            ResultStatus.Error(
                when (e) {
                    is IOException -> ChatError.NetworkError
                    is SQLiteException -> ChatError.DatabaseError
                    else -> ChatError.UnknownError(e.localizedMessage ?: "Failed to load history")
                }
            )
        }
    }

    private fun createUserQuestionEntity(question: String): AIChatTextEntity =
        AIChatTextEntity(
            text = question,
            from = AIChatTextType.USER_QUESTION.name,
            datetime = System.currentTimeMillis()
        )

    private fun createAIAnswerEntity(answer: String): AIChatTextEntity =
        AIChatTextEntity(
            text = answer,
            from = AIChatTextType.AI_ANSWER.name,
            datetime = System.currentTimeMillis()
        )
}