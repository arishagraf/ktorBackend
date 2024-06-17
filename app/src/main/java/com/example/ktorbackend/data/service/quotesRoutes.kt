package com.example.ktorbackend.data.service

import com.example.ktorbackend.data.service.RoutesConstants.GET_QUOTES_ROUTE
import com.example.ktorbackend.data.service.RoutesConstants.MODIFIER_QUOTE_ROUTE
import com.example.ktorbackend.data.service.RoutesConstants.MODIFY_BY_PARAM
import com.example.ktorbackend.domain.QuotesModel
import com.example.ktorbackend.domain.QuotesService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.quotesRoutes(quotesService: QuotesService) {
    route(GET_QUOTES_ROUTE) {
        get {
            call.respond(quotesService.getAllQuotes())
        }
        post {
            val quotesModel = call.receive<QuotesModel>()
            quotesService.addQuote(quotesModel)
            call.respond(quotesModel)
        }
        put(MODIFIER_QUOTE_ROUTE) {
            val id = call.parameters[MODIFY_BY_PARAM]?.toLongOrNull()
            id?.let {
                val updatedQuotesModel = call.receive<QuotesModel>()
                quotesService.updateQuote(it, updatedQuotesModel)
                call.respond(updatedQuotesModel)
            }
        }
        delete("/$MODIFIER_QUOTE_ROUTE") {
            val id = call.parameters[MODIFY_BY_PARAM]?.toLongOrNull()
            id?.let {
                quotesService.deleteQuote(it)
                call.respondText("Deleted quote with id $id", status = HttpStatusCode.OK)
            }
        }
    }
}
