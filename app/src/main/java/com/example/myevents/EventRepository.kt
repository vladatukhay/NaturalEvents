package com.example.myevents

import androidx.lifecycle.MutableLiveData
import com.example.myevents.data.EventNetworkModel
import com.example.myevents.retrofit.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventRepository {

    var listOfEvents: MutableLiveData<EventNetworkModel> = MutableLiveData()

    fun getServiceApiCall(): MutableLiveData<EventNetworkModel> {

        val call = Retrofit.apiService.getEvents()

        call.enqueue(object : Callback<EventNetworkModel> {
            override fun onResponse(
                    call: Call<EventNetworkModel>,
                    response: Response<EventNetworkModel>
            ) {
                listOfEvents.setValue(response.body())
            }

            override fun onFailure(call: Call<EventNetworkModel>, t: Throwable) {
                listOfEvents.postValue(null)
            }
        })
        return listOfEvents
    }
}