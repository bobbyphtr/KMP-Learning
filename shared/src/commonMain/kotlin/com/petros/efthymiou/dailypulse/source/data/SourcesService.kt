package com.petros.efthymiou.dailypulse.source.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesService(val httpClient: HttpClient) {

    private val apiKey: String = "f67ace1b27b24ce4b95c7f71fde88920"
    suspend fun fetchSources(): List<SourcesRaw> {
        val response: SourcesResponse = httpClient.get("https://newsapi.org/v2/top-headlines/sources?apiKey=$apiKey").body()
        return response.sources
    }

}