package com.egitof.chat.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AIChatHistoryDao {
    @Insert
    suspend fun insert(aiChatText: AIChatTextEntity)

    @Insert
    suspend fun insertAll(vararg aiChatText: AIChatTextEntity)

    @Query("SELECT * FROM AIChatTextEntity ORDER BY datetime ASC")
    suspend fun getAll(): List<AIChatTextEntity>

    @Query("DELETE FROM AIChatTextEntity")
    suspend fun clearHistory()
}