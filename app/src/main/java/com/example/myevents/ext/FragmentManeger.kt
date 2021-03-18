package com.example.myevents

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myevents.R


fun FragmentManager.detach() {
    findFragmentById(R.id.nav_host_fragment)?.also {
        beginTransaction().detach(it).commit()
    }
}

fun FragmentManager.attach(fragment: Fragment, tag: String) {
    if (fragment.isDetached) {
        beginTransaction().attach(fragment).commit()
    } else {
        beginTransaction().add(R.id.nav_host_fragment, fragment, tag).commit()
    }
    // Set a transition animation for this transaction.
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
}

fun FragmentManager.switchFragment(fragment: Fragment, tag: String): Boolean {
    return fragment.let {
        if (it.isAdded) return false
        detach()
        attach(it, tag)

        // Immediately execute transactions
        executePendingTransactions()
    }
}