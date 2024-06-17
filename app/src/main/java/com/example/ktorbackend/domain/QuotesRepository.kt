package com.example.ktorbackend.domain

interface QuotesRepository {
    fun getAllQuotes(): List<QuotesModel>
    fun addQuote(quotesModel: QuotesModel)
    fun updateQuote(id: Long, quotesModel: QuotesModel)
    fun deleteQuote(id: Long)
}