package com.example.myevents

import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Make selected position active
 */
fun BottomNavigationView.active(position: Int) {
    menu.getItem(position).isChecked = true
}