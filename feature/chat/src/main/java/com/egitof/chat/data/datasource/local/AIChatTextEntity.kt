package com.egitof.chat.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AIChatTextEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val from: String,
    val datetime: Long,
    val text: String
)