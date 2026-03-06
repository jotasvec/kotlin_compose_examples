package org.composedesktopapp.project.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol
import io.ktor.http.isSuccess
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json


class APIService {
    private val httpClient = HttpClient{
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
        install(HttpTimeout){
            requestTimeoutMillis = 15000
        }
    }
    suspend fun fetchData(): String {
        val result = httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "dummyjson.com"
                path("test")
            }
        }

        return when {
            result.status.isSuccess() -> result.bodyAsText()
            result.isActive -> "Loading ... "
            else -> result.status.description
        }
        /*return if (result.status.isSuccess()) {
            result.bodyAsText()
        } else result.status.description */
    }
}