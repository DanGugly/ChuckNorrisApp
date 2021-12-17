package com.example.chucknorrisapp.rest

import com.example.chucknorrisapp.model.Jokes
import com.example.chucknorrisapp.model.Value
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    @GET(JOKES+JOKE_COUNT)
    fun getRandomJokes() : Single<Jokes>

    @GET(JOKES)
    fun getRandomJoke() : Single<Value>

    @GET(JOKES)
    fun getNewCharJokes(
        @Query("firstName") first : String,
        @Query("lastName") last : String
    ) : Single<Value>

    companion object{
        const val BASE_URL = "http://api.icndb.com/"
        private const val JOKES = "jokes/random/"
        private const val JOKE_COUNT = "20"
    }
}