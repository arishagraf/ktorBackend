package com.example.ktorbackend.api

import com.example.ktorbackend.database.QuotesModel
import com.example.ktorbackend.di.appModule
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject
import org.koin.core.context.startKoin

fun main() {
    val server = embeddedServer(Netty, port = 8080) {

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

            route("/quotes") {
                get {
                    call.respond(quotesService.getAllQuotes())
                }
                post {
                    val quotesModel = call.receive<QuotesModel>()
                    quotesService.addQuote(quotesModel)
                    call.respond(quotesModel)
                }
                put("/{id}") {
                    val id = call.parameters["id"]?.toLongOrNull()
                    id?.let {
                        val updatedQuotesModel = call.receive<QuotesModel>()
                        quotesService.updateQuote(it, updatedQuotesModel)
                        call.respond(updatedQuotesModel)
                    }
                }
                delete("/{id}") {
                    val id = call.parameters["id"]?.toLongOrNull()
                    id?.let {
                        quotesService.deleteQuote(it)
                        call.respondText("Deleted news with id $id", status = HttpStatusCode.OK)
                    }
                }
            }
        }
    }

    server.start(wait = true)
}