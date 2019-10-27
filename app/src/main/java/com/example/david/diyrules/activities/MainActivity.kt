package com.example.david.diyrules.activities

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import com.example.david.diyrules.mediator.EventConsumer
import com.example.david.diyrules.R
import com.example.david.diyrules.actions.implementation.DisableDoNotDisturbAction
import com.example.david.diyrules.actions.implementation.EnableDoNotDisturbAction
import com.example.david.diyrules.mediator.EventMediator
import com.example.david.diyrules.receivers.PowerConnectedReceiver
import com.example.david.diyrules.rules.implementation.BasicRule
import com.example.david.diyrules.rules.interfaces.Rule
import com.example.david.diyrules.triggers.implementation.PowerConnectedEvent
import com.example.david.diyrules.triggers.implementation.PowerDisconnectedEvent

class MainActivity : AutoMateActivity() {
    companion object {
        const val tag: String = "Main Activity"
        const val MY_PERMISSION_REQUEST_ACCESS_NOTIFICATION_POLICY = 1
    }

    //TODO add rule management and possibility to persist
    private val turnOnDoNotDisturb: Rule = BasicRule(PowerConnectedEvent, EnableDoNotDisturbAction)
    private val turnOffDoNotDisturb: Rule = BasicRule(PowerDisconnectedEvent, DisableDoNotDisturbAction)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        registerReceiver(PowerConnectedReceiver, filter)

        turnOffDoNotDisturb.isActive = true
        turnOnDoNotDisturb.isActive = true




        resources.getString(R.string.power_connection_state_text)
/* findViewById<ToggleButton>(R.id.dndToggle).setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                Log.d(tag, "dnd toggle: ON")
                setDoNotDisturb(NotificationManager.INTERRUPTION_FILTER_NONE)
            } else {
                Log.d(tag, "dnd toggle: OFF")
                setDoNotDisturb(NotificationManager.INTERRUPTION_FILTER_ALL)
            }
        } */
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(PowerConnectedReceiver)
    }

    /*
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            MY_PERMISSION_REQUEST_ACCESS_NOTIFICATION_POLICY -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission was granted -> noice
                    Toast.makeText(this, "permission to access notification policy was granted! yay", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "permission to access notification policy was denied! boo", Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {
                // maybe ignore explicitly? for now: pass them on to the parent activity which will ignore them for us
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }*/
}
