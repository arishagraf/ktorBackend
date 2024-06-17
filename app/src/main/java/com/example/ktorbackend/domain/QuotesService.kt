package com.example.ktorbackend.domain

class QuotesService(private val quotesRepository: QuotesRepository) {

    fun getAllQuotes() = quotesRepository.getAllQuotes()

    fun addQuote(quotesModel: QuotesModel) {
        quotesRepository.addQuote(quotesModel)
    }

    fun updateQuote(id: Long, quotesModel: QuotesModel) {
        quotesRepository.updateQuote(id, quotesModel)
    }

    fun deleteQuote(id: Long) {
        quotesRepository.deleteQuote(id)
    }
}
