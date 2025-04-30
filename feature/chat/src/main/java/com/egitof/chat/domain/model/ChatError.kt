package com.egitof.chat.domain.model

sealed class ChatError {
    data object NetworkError : ChatError()
    data object DatabaseError : ChatError()
    data class UnknownError(val message: String) : ChatError()
}