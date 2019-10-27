package com.example.david.diyrules.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.david.diyrules.mediator.EventMediator
import com.example.david.diyrules.triggers.implementation.PowerConnectedEvent
import com.example.david.diyrules.triggers.implementation.PowerDisconnectedEvent


// to read power state: register a BroadcastReceiver for ACTION_POWER_CONNECTED and ACTION_POWER_DISCONNECTED
// based on this post: https://stackoverflow.com/a/6660230/5015377
// the registration is done in the app manifest

// also, for some wonky reason manifest-declared receivers are not allowed to receive implicit broadcasts starting with SDK version 26 (android oreo)
// workaround: set SDK version to 25 or lower
// source: https://commonsware.com/blog/2017/04/11/android-o-implicit-broadcast-ban.html
object PowerConnectedReceiver: BroadcastReceiver() {
    val tag: String = "PowerConnectedReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action

        if(action == Intent.ACTION_POWER_CONNECTED){
            Log.d(tag, "power connected;    1")
            EventMediator.onEvent(PowerConnectedEvent)

        } else if (action == Intent.ACTION_POWER_DISCONNECTED){
            Log.d(tag, "power disconnected; 0")
            EventMediator.onEvent(PowerDisconnectedEvent)
        }
    }

}