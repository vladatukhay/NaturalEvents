package com.example.myevents.retrofit

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Retrofit {

    val baseUrl = "https://eonet.sci.gsfc.nasa.gov/api/v3/"

    val apiService : APIService by lazy {
        Log.d("Retrofit", "Creating retrofit client")

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        // Create Retrofit client
        return@lazy retrofit.create(APIService::class.java)
    }
}