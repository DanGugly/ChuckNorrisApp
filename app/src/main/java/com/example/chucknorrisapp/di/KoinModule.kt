package com.example.chucknorrisapp.di

import com.example.chucknorrisapp.adapter.JokesRecyclerViewAdapter
import com.example.chucknorrisapp.rest.NetworkApi
import com.example.chucknorrisapp.view.ButtonFragment
import com.example.chucknorrisapp.view.JokesFragment
import com.example.chucknorrisapp.view.NewHeroFragment
import com.example.chucknorrisapp.viewmodel.JokeViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.sin

val networkModule = module {
    fun provideMoshi() = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    fun okHttpClient(loggingInterceptor : HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    fun provideNetworkApi(okHttpClient: OkHttpClient, moshi: Moshi) =
        Retrofit.Builder()
            .baseUrl(NetworkApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(NetworkApi::class.java)

    single { provideMoshi() }
    single { provideLoggingInterceptor() }
    single { okHttpClient(get()) }
    single { provideNetworkApi(get(), get()) }
}

val appModule = module {
    single { JokesRecyclerViewAdapter() }
    single { JokesFragment() }
    single { ButtonFragment() }
    single { NewHeroFragment() }
}

val viewModelModule = module {
    viewModel {
        JokeViewModel(get())
    }
}
