package com.example.ktorbackend.data.database

import java.sql.Connection
import java.sql.DriverManager

object DatabaseConnection {
    fun connect() : Connection {
        val jdbcUrl = "jdbc:mysql://MacBook-Air-Arina.local:3306/quotes_database"
        val dbUser = "root"
        val dbPassword = "123456-o"
        return DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)
    }
}