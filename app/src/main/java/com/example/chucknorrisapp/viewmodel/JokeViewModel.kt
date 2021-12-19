package com.example.chucknorrisapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chucknorrisapp.rest.NetworkApi
import com.example.chucknorrisapp.adapter.JokesRecyclerViewAdapter
import com.example.chucknorrisapp.model.Jokes
import com.example.chucknorrisapp.view.Contract
import kotlinx.coroutines.*

class JokeViewModel(
    private val jokeApi: NetworkApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope : CoroutineScope = CoroutineScope(ioDispatcher)
) : ViewModel() {

    private var contract : Contract? = null

    fun initContract(contract1: Contract){
        contract = contract1
    }

    fun getRandomJoke(){
        coroutineScope.launch {
            try {
                val response = jokeApi.getRandomJoke()
                if (response.isSuccessful){
                    response.body()?.let { jokes ->
                        val result = jokes.value[0].joke
                        Log.d("RandJ", "Result : $result")
                    } ?: Log.d("RandJ", "Null")
                } else{
                    Log.d("RandJ", "Issue")
                }
            } catch (e : Exception){
                Log.e("RandJ", e.stackTraceToString())
            }
        }
    }

    fun getRandomJokes(){
        coroutineScope.launch {
            try {
                val response = jokeApi.getRandomJokes()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        response.body()?.let { jokes ->
                            //jokesRecyclerViewAdapter.loadJokes(listOf(jokes))
                            contract?.newJokes(listOf(jokes))
                        } ?: Log.d("RandNEJ", "Null")
                    } else {
                        Log.d("RandNEJ", "Issue")
                    }
                }
            } catch (e : Exception){
                Log.e("RandNEJ", e.stackTraceToString())
            }
        }
    }

    fun getNewHeroJoke(firstLast : String){
        coroutineScope.launch {
            try {
                val response = jokeApi.getNewCharJokes("Spider", "Man")
                if (response.isSuccessful){
                    response.body()?.let { jokes ->
                        Log.d("RandHJ", jokes.value[0].joke)
                    } ?: Log.d("RandHJ", "Null")
                } else{
                    Log.d("RandHJ", "Issue")
                }
            } catch (e : Exception){
                Log.e("RandHJ", e.stackTraceToString())
            }
        }
    }
}
