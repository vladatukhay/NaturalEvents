package com.example.myevents.retrofit


import com.example.myevents.data.EventNetworkModel
import retrofit2.Call
import retrofit2.http.GET


interface APIService {

    @GET("events?source=InciWeb,EO")
    fun getEvents(): Call<EventNetworkModel> //Deferred<Response<Events>>

}