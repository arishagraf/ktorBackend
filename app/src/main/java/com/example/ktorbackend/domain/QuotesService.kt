package com.example.ktorbackend.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotesService(private val quotesRepository: QuotesRepository) {

    suspend fun getAllQuotes() = quotesRepository.getAllQuotes()

    fun addQuote(quotesModel: QuotesModel) {
        CoroutineScope(Dispatchers.IO).launch {
            quotesRepository.addQuote(quotesModel)
        }
    }

    fun updateQuote(id: Long, quotesModel: QuotesModel) {
        CoroutineScope(Dispatchers.IO).launch {
            quotesRepository.updateQuote(id, quotesModel)
        }
    }

    fun deleteQuote(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            quotesRepository.deleteQuote(id)
        }
    }
}
