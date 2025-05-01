package com.egitof.chat.presentation.viewmodel.state

data class AiChatUiState(
    val isLoading: Boolean = false,
    val messages: List<ChatMessage> = emptyList(),
    val currentInput: String = "",
    val fieldState: AiChatFieldState = AiChatFieldState.Default
) {
    data class ChatMessage(
        val text: String,
        val isFromUser: Boolean
    )
    sealed interface AiChatFieldState {
        object Default : AiChatFieldState
        object EmptyQuestion : AiChatFieldState
    }
}
