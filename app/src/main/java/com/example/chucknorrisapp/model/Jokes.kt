package com.example.chucknorrisapp.model


import com.squareup.moshi.Json

data class Jokes(
    @Json(name = "type")
    val type: String,
    @Json(name = "value")
    val value: List<Value>
)