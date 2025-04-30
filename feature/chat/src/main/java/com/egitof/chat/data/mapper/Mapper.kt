package com.egitof.chat.data.mapper

import com.egitof.chat.data.datasource.local.AIChatTextEntity
import com.egitof.chat.domain.model.AIChatText
import com.egitof.chat.domain.model.AIChatTextType

fun AIChatTextEntity.toDomain() : AIChatText =
    when(this.from) {
        AIChatTextType.USER_QUESTION.name -> AIChatText.UserQuestion(question = this.text)
        AIChatTextType.AI_ANSWER.name -> AIChatText.AIAnswer(answer = this.text)
        else -> throw IllegalArgumentException("Invalid from value: ${this.from}")
    }

fun List<AIChatTextEntity>.toDomain() : List<AIChatText> =
    this.map { entity -> entity.toDomain() }