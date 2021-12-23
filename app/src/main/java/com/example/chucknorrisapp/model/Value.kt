package com.example.chucknorrisapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// It's always important when using moshi to add the generated adapter
// may be when doing the network call works, but when we want to pass objects or store them in shared preferences
// we need to serialize it, and that will happen using moshi and the adapters
@JsonClass(generateAdapter = true)
data class Value(
    @Json(name = "categories")
    val categories: List<String>?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "joke")
    val joke: String
)