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

    fun getRandomJoke(){
        coroutineScope.launch {
            try {
                val response =
                    // here you can use the same getRandomJoke for explicit
                    // no need to have extra duplicated code
                    jokeApi.getRandomJoke()
                response.body()?.let { jokes ->
                    _randomJoke.postValue(UIState.SUCCESS_SINGLE(jokes))
                } ?: _randomJoke.postValue(UIState.ERROR(Throwable("Response is null")))

            } catch (e : Exception){
                _randomJoke.postValue(UIState.ERROR(e))
            }
        }
    }

    fun getRandomJokes(){
        coroutineScope.launch {
            try {
                val response = jokeApi.getRandomJokes()
                response.body()?.let { jokes ->
                            _allJokes.postValue(UIState.SUCCESS(listOf(jokes)))
                        } ?: _allJokes.postValue(UIState.ERROR(Throwable("Response is null")))
            } catch (e : Exception){
                _allJokes.postValue(UIState.ERROR(e))
            }
        }
    }

    fun getNewHeroJoke(first: String, last: String){
        coroutineScope.launch {
            try {
                val response = jokeApi.getRandomJoke(first, last)
                response.body()?.let { jokes ->
                    _heroJoke.postValue(UIState.SUCCESS_SINGLE(jokes))
                } ?: _heroJoke.postValue(UIState.ERROR(Throwable("Response is null")))
            } catch (e : Exception){
                _heroJoke.postValue(UIState.ERROR(e))
            }
        }
    }
}
