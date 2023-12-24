package com.example.suitmediatest.model

data class ListResponse (
    val page:Int,
    val per_page:Int,
    val total:Int,
    val total_pages:Int,
    val data: List<User>
)