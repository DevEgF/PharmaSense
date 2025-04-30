package com.egitof.chat.domain.model

sealed class AIChatText {
    data class UserQuestion(val question: String): AIChatText()
    data class AIAnswer(val answer: String): AIChatText()
}