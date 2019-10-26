package com.example.david.diyrules

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
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import com.example.david.diyrules.receivers.PowerConnectedReceiver
import com.example.david.diyrules.ruleEvents.PowerConnectedEvent
import com.example.david.diyrules.ruleEvents.PowerDisconnectedEvent

class MainActivity : AppCompatActivity() {
    val tag: String = "Main Activity"
    val MY_PERMISSION_REQUEST_ACCESS_NOTIFICATION_POLICY = "my permission request access notification policy".hashCode()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        registerReceiver(PowerConnectedReceiver, filter)

        ReceiverMediator.subscribe(EventConsumer(::onPowerConnected), PowerConnectedEvent)
        ReceiverMediator.subscribe(EventConsumer(::onPowerDisconnected), PowerDisconnectedEvent)

        resources.getString(R.string.power_connection_state_text)

        findViewById<ToggleButton>(R.id.dndToggle).setOnCheckedChangeListener{_, isChecked ->
            if(isChecked){
                Log.d(tag, "dnd toggle: ON")
                setDoNotDisturb(NotificationManager.INTERRUPTION_FILTER_NONE)
            } else {
                Log.d(tag, "dnd toggle: OFF")
                setDoNotDisturb(NotificationManager.INTERRUPTION_FILTER_ALL)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(PowerConnectedReceiver)
    }

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
    }

    private fun setDoNotDisturb(interruptFilter: Int){
        Log.d(tag, "trying to access notification policy")
        if(ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_NOTIFICATION_POLICY) == PackageManager.PERMISSION_GRANTED) {
            // we already have permission to access notification policy - we do not care about permissions any more
            Log.d(tag, "permission to access notification policy already granted - access away!!")
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if(notificationManager.isNotificationPolicyAccessGranted) {
                notificationManager.setInterruptionFilter(interruptFilter)
            } else {
                startActivityForResult(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS), 0)
            }

        } else {
            //we do not have permission to access notification policy :(
            // --> request it now ! (we'll ignore showing a rationale for now - this app is just for my personal use)
            //TODO: add permission rationale handling
            Log.d(tag, "permission not granted -> requesting permission")
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_NOTIFICATION_POLICY), MY_PERMISSION_REQUEST_ACCESS_NOTIFICATION_POLICY)
        }
    }


    private fun onPowerConnected(){
        val view: TextView = findViewById(R.id.powerConnectedTextView)
        val string = String.format(resources.getString(R.string.power_connection_state_text), resources.getString(R.string.power_connected))
        view.text = string

        setDoNotDisturb(NotificationManager.INTERRUPTION_FILTER_NONE)
    }

    private fun onPowerDisconnected(){
        val view: TextView = findViewById(R.id.powerConnectedTextView)
        val string = String.format(resources.getString(R.string.power_connection_state_text), resources.getString(R.string.power_disconnected))
        view.text = string

        setDoNotDisturb(NotificationManager.INTERRUPTION_FILTER_ALL)
    }
}
