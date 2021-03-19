package com.example.myevents.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myevents.EventRepository
import com.example.myevents.data.EventNetworkModel
import kotlinx.coroutines.launch


class EventViewModel : ViewModel() {

    private val repository: EventRepository = EventRepository()

    private var eventsLiveData: MutableLiveData<EventNetworkModel>? = null


    fun getEonetEvents() : LiveData<EventNetworkModel>? {
        viewModelScope.launch {
            eventsLiveData = repository.getServiceApiCall()
        }
        return eventsLiveData
    }

}