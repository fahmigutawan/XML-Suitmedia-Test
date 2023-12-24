package com.example.suitmediatest.data.data_source

import com.example.suitmediatest.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val client: HttpClient
) {
    val baseUrl = BuildConfig.BASE_URL

    suspend fun getList(page: Int) = client.get("$baseUrl/users?page=${page}&per_page=10") {
        contentType(ContentType.Application.Json)
    }

}