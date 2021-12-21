package com.example.chucknorrisapp.utils

import com.example.chucknorrisapp.model.Jokes

sealed class UIState{
    class LOADING(val isLoading : Boolean = true) : UIState()
    class SUCCESS(val jokes: List<Jokes>) : UIState()
    class ERROR(val error: Throwable) : UIState()
}
