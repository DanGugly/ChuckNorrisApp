package com.example.chucknorrisapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Jokes(
    @Json(name = "type")
    val type: String,
    @Json(name = "value")
    val value: List<Value>
)

@JsonClass(generateAdapter = true)
data class SingleJoke(
    @Json(name = "type")
    val type: String,
    @Json(name = "value")
    val value: Value
)