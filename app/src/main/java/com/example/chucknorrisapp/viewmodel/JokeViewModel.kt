package com.example.chucknorrisapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chucknorrisapp.rest.NetworkApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class JokeViewModel(
    private val jokeApi: NetworkApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope : CoroutineScope = CoroutineScope(ioDispatcher)
) : ViewModel() {

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