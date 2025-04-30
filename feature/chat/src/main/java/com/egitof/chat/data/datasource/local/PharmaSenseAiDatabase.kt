package com.egitof.chat.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_NAME = "pharmasense_ai_db"

@Database(entities = [AIChatTextEntity::class], version = 1, exportSchema = false)
abstract class PharmaSenseAiDatabase: RoomDatabase() {
    abstract fun aiChatHistoryDao(): AIChatHistoryDao
}