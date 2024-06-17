package com.example.ktorbackend.domain

interface QuotesRepository {
    suspend fun getAllQuotes(): List<QuotesModel>
    suspend fun addQuote(quotesModel: QuotesModel)
    suspend fun updateQuote(id: Long, quotesModel: QuotesModel)
    suspend fun deleteQuote(id: Long)
}