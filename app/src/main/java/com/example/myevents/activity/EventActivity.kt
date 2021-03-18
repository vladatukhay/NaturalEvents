package com.example.myevents.activity

import android.app.ActionBar
import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myevents.R
import com.example.myevents.databinding.ActivityEventBinding

class EventActivity : Activity() {

    private lateinit var binding: ActivityEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_event)

        val actionBar: ActionBar
    }
}