package com.example.ktorbackend.di

import com.example.ktorbackend.data.database.QuotesRepositoryImpl
import com.example.ktorbackend.domain.QuotesService
import com.example.ktorbackend.data.database.DatabaseConnection
import com.example.ktorbackend.domain.QuotesRepository
import org.koin.dsl.module

val appModule = module {
    single { DatabaseConnection }
    single { QuotesService(get()) }
    single<QuotesRepository> { QuotesRepositoryImpl(get()) }
}
