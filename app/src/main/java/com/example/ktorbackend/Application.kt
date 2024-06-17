package com.example.ktorbackend.service

import com.example.ktorbackend.data.service.quotesRoutes
import com.example.ktorbackend.di.appModule
import com.example.ktorbackend.domain.QuotesService
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

fun Application.module() {

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    startKoin {
        modules(appModule)
    }

    routing {
        val quotesService by inject<QuotesService>(QuotesService::class.java)
        quotesRoutes(quotesService)
    }
}
