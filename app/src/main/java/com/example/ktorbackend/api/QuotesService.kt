package com.example.ktorbackend.api

import com.example.ktorbackend.database.Database
import com.example.ktorbackend.database.QuotesModel

class QuotesService(private val database: Database) {

    fun getAllQuotes(): List<QuotesModel> {
        val connection = database.connect()
        val stmt = connection.createStatement()
        val resultSet = stmt.executeQuery("SELECT * FROM quotes")
        val quotesModelList = mutableListOf<QuotesModel>()

        while (resultSet.next()) {
            val id = resultSet.getLong("id")
            val title = resultSet.getString("author")
            val content = resultSet.getString("content")
            val quotesModel = QuotesModel(id, title, content)
            quotesModelList.add(quotesModel)
        }

        connection.close()
        return quotesModelList
    }

    fun addQuote(quotesModel: QuotesModel) {
        val connection = database.connect()
        val stmt = connection.prepareStatement("INSERT INTO quotes (author, content) VALUES (?, ?)")
        stmt.setString(1, quotesModel.author)
        stmt.setString(2, quotesModel.content)
        stmt.executeUpdate()

        connection.close()
    }

    fun updateQuote(id: Long, updatedQuotesModel: QuotesModel) {
        val connection = database.connect()
        val stmt = connection.prepareStatement("UPDATE quotes SET author = ?, content = ? WHERE id = ?")
        stmt.setString(1, updatedQuotesModel.author)
        stmt.setString(2, updatedQuotesModel.content)
        stmt.setLong(3, id)
        stmt.executeUpdate()

        connection.close()
    }

    fun deleteQuote(id: Long) {
        val connection = database.connect()
        val stmt = connection.prepareStatement("DELETE FROM quotes WHERE id = ?")
        stmt.setLong(1, id)
        stmt.executeUpdate()

        connection.close()
    }
}
