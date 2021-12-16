package com.example.chucknorrisapp.model


import com.squareup.moshi.Json

data class Value(
    @Json(name = "categories")
    val categories: List<String>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "joke")
    val joke: String
)