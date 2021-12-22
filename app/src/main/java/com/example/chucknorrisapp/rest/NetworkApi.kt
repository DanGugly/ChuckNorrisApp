package com.example.chucknorrisapp.rest

import com.example.chucknorrisapp.model.Jokes
import com.example.chucknorrisapp.model.Value
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    @GET(JOKES+JOKE_COUNT_20)
    suspend fun getRandomJokes(
        @Query("escape") escapeJS : String = ESCAPE_JAVASCRIPT
    ) : Response<Jokes>

    @GET(JOKES+ JOKE_COUNT_2)
    suspend fun getRandomJoke(
        @Query("escape") escapeJS : String = ESCAPE_JAVASCRIPT
    ) : Response<Jokes>

    @GET(JOKES+ JOKE_COUNT_2)
    suspend fun getNewCharJokes(
        //Always put parameters passed to function first
        @Query("firstName") first : String,
        @Query("lastName") last : String,
        @Query("escape") escapeJS : String = ESCAPE_JAVASCRIPT
    ) : Response<Jokes>

    @GET(JOKES+JOKE_COUNT_20)
    suspend fun getRandomJokesClean(
        @Query("exclude") exclude : String = EXPLICIT,
        @Query("escape") escapeJS : String = ESCAPE_JAVASCRIPT
    ) : Response<Jokes>

    @GET(JOKES+ JOKE_COUNT_2)
    suspend fun getRandomJokeClean(
        @Query("exclude") exclude : String = EXPLICIT,
        @Query("escape") escapeJS : String = ESCAPE_JAVASCRIPT
    ) : Response<Jokes>

    @GET(JOKES+ JOKE_COUNT_2)
    suspend fun getNewCharJokesClean(
        @Query("firstName") first : String,
        @Query("lastName") last : String,
        @Query("exclude") exclude : String = EXPLICIT,
        @Query("escape") escapeJS : String = ESCAPE_JAVASCRIPT
    ) : Response<Jokes>
    companion object{
        const val BASE_URL = "http://api.icndb.com/"
        private const val ESCAPE_JAVASCRIPT = "javascript"
        private const val EXPLICIT = "[explicit]"
        private const val JOKES = "jokes/random/"
        private const val JOKE_COUNT_20 = "20"
        //Getting an issue with moshi when trying to pull a single joke
        //com.squareup.moshi.JsonDataException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at path $.value
        //Pulling as an array of 2 instead of a single object seems to work
        private const val JOKE_COUNT_2 = "2"
    }
}