package com.example.chucknorrisapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.chucknorrisapp.rest.NetworkApi
import kotlinx.coroutines.*

class JokeViewModel(
    private val jokeApi: NetworkApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope : CoroutineScope = CoroutineScope(ioDispatcher)
) : ViewModel() {

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
                if (response.isSuccessful){
                    response.body()?.let { jokes ->
                        jokes.toString()
                    } ?: Log.d("RandJ", "Null")
                } else{
                    Log.d("RandJ", "Issue")
                }
            } catch (e : Exception){
                Log.e("RandJ", e.localizedMessage)
            }
        }
    }

    /*
    fun getAllFruits(){
        coroutineScope.launch {
            //Perform the task in the worker thread
            try {
                //Retrieve all fruits from network call
                val response = fruitApi.retrieveAllFruits()
                // Switch to main thread
                withContext(Dispatchers.Main){
                    // Whatever happens in here will happen in the main thread

                }
                if (response.isSuccessful){
                    //Check for non nullable value of body
                    response.body()?.let { fruits ->
                        //Live data follows the observable pattern and is lifecycle aware
                        // Value of body here is non nullable
                        // Postvalue will update in the worker thread, and will be observed in the main thread by the live data
                        _allFruits.postValue(UIState.Success(fruits))
                        //Elvis operator on what to perform if the body is null
                    } ?: _allFruits.postValue(UIState.Error(IllegalStateException("Body response is null")))
                } else {
                    _allFruits.postValue(UIState.Error(
                        //We specifically use string instead of toString here
                        Throwable(response.errorBody()?.string())
                    ))
                }
            } catch (e: Exception){
                _allFruits.postValue(UIState.Error(e))
            }
        }
    } */
}