package com.example.suitmediatest.data.data_source

import io.ktor.client.HttpClient
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val client: HttpClient
) {
}