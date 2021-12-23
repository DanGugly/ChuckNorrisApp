package com.example.chucknorrisapp.utils

import com.example.chucknorrisapp.model.Jokes
import com.example.chucknorrisapp.model.SingleJoke

sealed class UIState{
    class LOADING(val isLoading : Boolean = true) : UIState()
    class SUCCESS(val jokes: List<Jokes>) : UIState()
    class SUCCESS_SINGLE(val joke: SingleJoke) : UIState()
    class ERROR(val error: Throwable) : UIState()
}
