package com.example.ktorbackend.di

import com.example.ktorbackend.api.QuotesService
import com.example.ktorbackend.database.Database
import org.koin.dsl.module

val appModule = module {
    single { Database }
    single { QuotesService(get()) }
}
