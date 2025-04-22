package com.egitof.chat.data.api

import com.google.ai.client.generativeai.GenerativeModel

class AiApiServiceImpl : AiApiService {

    private val generativeModel = GenerativeModel(
        modelName = GENERATIVE_MODEL_NAME, apiKey = "AIzaSyBix2nmNSEPGAtg2SWxHNsq972qL2AQ_nU"
    )

    override suspend fun sendPrompt(question: String): String? =
        try {
            val customPrompt = generatePrompt(question)
            val response = generativeModel.generateContent(prompt = customPrompt)
            response.text
        } catch (_: Exception) {
            null
        }

    private fun generatePrompt(question: String): String = """
    Você é um assistente farmacêutico especializado em farmácias de manipulação, 
    com profundo conhecimento em formulações, legislação, boas práticas de manipulação, 
    cálculos farmacêuticos e atendimento técnico.

    Sua tarefa é auxiliar farmacêuticos e profissionais da área fornecendo:

    1) Explicações claras, objetivas e embasadas cientificamente.
    2) Cálculos farmacêuticos detalhados, quando necessário.
    3) Sugestões de formulações, ajustes e boas práticas de manipulação.
    4) Referências a legislações (como RDCs da Anvisa), fontes técnicas confiáveis 
       e literatura científica, quando aplicável.

    Aqui está a pergunta do(a) profissional: $question

    Forneça uma resposta técnica, didática e prática, com foco em farmácias de manipulação, 
    que ajude o profissional a tomar decisões com segurança e confiança.
    """.trimIndent()

    private companion object {
        const val GENERATIVE_MODEL_NAME = "gemini-1.5-flash"
    }
}
