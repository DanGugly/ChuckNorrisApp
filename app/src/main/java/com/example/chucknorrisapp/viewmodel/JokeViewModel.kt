package com.example.chucknorrisapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chucknorrisapp.rest.NetworkApi
import com.example.chucknorrisapp.model.Jokes
import com.example.chucknorrisapp.utils.UIState
import kotlinx.coroutines.*

class JokeViewModel(
    private val jokeApi: NetworkApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope : CoroutineScope = CoroutineScope(ioDispatcher)
) : ViewModel() {

    private var _allJokes: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val allJokesObserver: LiveData<UIState> get() = _allJokes

    private var _randomJoke: MutableLiveData<Jokes?> = MutableLiveData(null)
    val randomJokeObserver: LiveData<Jokes?> get() = _randomJoke

    fun setRandomJoke(joke : Jokes){
        _randomJoke.postValue(joke)
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
                //withContext(Dispatchers.Main) {
                        response.body()?.let { jokes ->
                            _allJokes.postValue(UIState.SUCCESS(listOf(jokes)))
                        } ?: _allJokes.postValue(UIState.ERROR(Throwable("Response is null")))

                //}
            } catch (e : Exception){
                _allJokes.postValue(UIState.ERROR(e))
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
