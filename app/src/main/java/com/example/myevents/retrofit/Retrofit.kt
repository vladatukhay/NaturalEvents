package com.example.myevents.retrofit

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Retrofit {

    val baseUrl = "https://eonet.sci.gsfc.nasa.gov/api/v3/"

    val retrofitClient : Retrofit.Builder by lazy {
        Log.d("Retrofit", "Creating retrofit client")

        Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

    val apiService: APIService by lazy {
        retrofitClient
                .build()
                .create(APIService::class.java)
    }
}