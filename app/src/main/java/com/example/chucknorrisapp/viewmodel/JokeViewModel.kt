package com.example.chucknorrisapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chucknorrisapp.rest.NetworkApi
import com.example.chucknorrisapp.utils.UIState
import kotlinx.coroutines.*

class JokeViewModel(
    private val jokeApi: NetworkApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope : CoroutineScope = CoroutineScope(ioDispatcher)
) : ViewModel() {

    private var _allJokes: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val allJokesObserver: LiveData<UIState> get() = _allJokes

    private var _randomJoke: MutableLiveData<UIState> = MutableLiveData(null)
    val randomJokeObserver: LiveData<UIState> get() = _randomJoke

    private var _heroJoke: MutableLiveData<UIState> = MutableLiveData(null)
    val heroJokeObserver: LiveData<UIState> get() = _heroJoke

    fun getRandomJoke(nonExplicit : Boolean){
        coroutineScope.launch {
            try {
                val response = if (nonExplicit){
                    jokeApi.getRandomJokeClean()
                } else{
                    jokeApi.getRandomJoke()
                }
                response.body()?.let { jokes ->
                    _randomJoke.postValue(UIState.SUCCESS_SINGLE(jokes))
                } ?: _randomJoke.postValue(UIState.ERROR(Throwable("Response is null")))
            } catch (e : Exception){
                _randomJoke.postValue(UIState.ERROR(e))
            }
        }
    }

    fun getRandomJokes(nonExplicit: Boolean){
        coroutineScope.launch {
            try {
                val response = if (nonExplicit){
                    jokeApi.getRandomJokesClean()
                } else{
                    jokeApi.getRandomJokes()
                }
                response.body()?.let { jokes ->
                            _allJokes.postValue(UIState.SUCCESS(listOf(jokes)))
                        } ?: _allJokes.postValue(UIState.ERROR(Throwable("Response is null")))
            } catch (e : Exception){
                _allJokes.postValue(UIState.ERROR(e))
            }
        }
    }

    fun getNewHeroJoke(first: String, last: String, nonExplicit: Boolean){
        coroutineScope.launch {
            try {
                val response = if (nonExplicit){
                    jokeApi.getNewCharJokesClean(first, last)
                } else{
                    jokeApi.getNewCharJokes(first, last)
                }
                response.body()?.let { jokes ->
                    _heroJoke.postValue(UIState.SUCCESS_SINGLE(jokes))
                } ?: _heroJoke.postValue(UIState.ERROR(Throwable("Response is null")))
            } catch (e : Exception){
                _heroJoke.postValue(UIState.ERROR(e))
            }
        }
    }
}
