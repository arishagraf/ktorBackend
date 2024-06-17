package com.example.ktorbackend.data.database

import com.example.ktorbackend.domain.QuotesModel
import com.example.ktorbackend.domain.QuotesRepository

class QuotesRepositoryImpl(
    private val database: DatabaseConnection
) : QuotesRepository {

    override fun getAllQuotes(): List<QuotesModel> {
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

    override fun addQuote(quotesModel: QuotesModel) {
        val connection = database.connect()
        val stmt = connection.prepareStatement("INSERT INTO quotes (author, content) VALUES (?, ?)")
        stmt.setString(1, quotesModel.author)
        stmt.setString(2, quotesModel.content)
        stmt.executeUpdate()

        connection.close()
    }

    override fun updateQuote(id: Long, quotesModel: QuotesModel) {
        val connection = database.connect()
        val stmt =
            connection.prepareStatement("UPDATE quotes SET author = ?, content = ? WHERE id = ?")
        stmt.setString(1, quotesModel.author)
        stmt.setString(2, quotesModel.content)
        stmt.setLong(3, id)
        stmt.executeUpdate()

        connection.close()
    }

    override fun deleteQuote(id: Long) {
        val connection = database.connect()
        val stmt = connection.prepareStatement("DELETE FROM quotes WHERE id = ?")
        stmt.setLong(1, id)
        stmt.executeUpdate()

        connection.close()
    }
}
