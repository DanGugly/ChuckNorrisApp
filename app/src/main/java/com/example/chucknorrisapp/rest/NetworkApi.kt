package com.example.chucknorrisapp.rest

import com.example.chucknorrisapp.model.Jokes
import com.example.chucknorrisapp.model.SingleJoke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * I made changes here to reduce the duplicated functions
 *
 * SOLID principles is also about avoid duplications
 */
interface NetworkApi {

    @GET(JOKES + JOKE_COUNT_20)
    suspend fun getRandomJokes(
        @Query("firstName") first : String? = null,
        @Query("lastName") last : String? = null,
        @Query("exclude") exclude : String? = if (isNonExplicit) EXPLICIT else null
    ) : Response<Jokes>

    @GET(JOKES)
    suspend fun getRandomJoke(
        @Query("firstName") first : String? = null,
        @Query("lastName") last : String? = null,
        @Query("exclude") exclude : String? = if (isNonExplicit) EXPLICIT else null
    ) : Response<SingleJoke>

    /**
     * I am adding here the flag for the non explicit jokes
     * So instead of passing it around we set that once here.
     *
     * For the issue with a single joke, is better to have the specific POJO for that response than
     * having the work around.
     *
     * The value for javascript was not really needed, lets avoid having parameter that we not really need
     */
    companion object {
        var isNonExplicit = false

        const val BASE_URL = "http://api.icndb.com/"
        private const val EXPLICIT = "explicit"
        private const val JOKES = "jokes/random/"

        // there is no need of separate this number right?
        // avoid extra variables if not really needed
        private const val JOKE_COUNT_20 = "20"

        //Getting an issue with moshi when trying to pull a single joke
        //com.squareup.moshi.JsonDataException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at path $.value
        //Pulling as an array of 2 instead of a single object seems to work

        // no need of passing the number 2 and get the list and just select the first one
        // is a good work around, but is better to add the specific object that handles this scenario

        // private const val JOKE_COUNT_2 = "2"
    }
}