package com.example.myevents


import androidx.fragment.app.Fragment
import com.example.myevents.fragments.EventsFragment
import com.example.myevents.fragments.InfoFragment

enum class BottomNavigationPosition(val position: Int, val id: Int) {
    HOME(0, R.id.nav_graph_home),
    INFO(1, R.id.nav_graph_info),

}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.HOME.id -> BottomNavigationPosition.HOME
    BottomNavigationPosition.INFO.id -> BottomNavigationPosition.INFO
    else -> BottomNavigationPosition.HOME
}

fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.HOME -> EventsFragment.newInstance()
    BottomNavigationPosition.INFO -> InfoFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.HOME -> EventsFragment.TAG
    BottomNavigationPosition.INFO -> InfoFragment.TAG
}