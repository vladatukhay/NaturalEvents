package com.example.myevents.activity

import android.app.ActionBar
import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myevents.R
import com.example.myevents.databinding.ActivityEventBinding

class EventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_event)

        val eventTitle = intent.getStringExtra("event_title")

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setTitle(eventTitle)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}