package com.example.myevents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myevents.data.Events
import com.example.myevents.databinding.SingleItemEventListBinding

interface EventClickListener {
    fun onItemClick(event: Events)
}

class EventAdapter(var mEventList: List<Events>, val itemClickListener: EventClickListener) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() { //private val clickListener: (EventNetworkModel) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleItemEventListBinding.inflate(inflater, parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event: Events = mEventList[position]
        holder.bind(event, itemClickListener)
    }

    override fun getItemCount(): Int {
        return mEventList.size
    }


    class EventViewHolder(private val binding: SingleItemEventListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Events, clickListener: EventClickListener) {
            binding.eventTitle.text = event.title

//            for ( source in event.sources) {
//                binding.userLocation.text = source.url
//            }

            binding.root.setOnClickListener {
                clickListener.onItemClick(event)
            }
        }

    }
}