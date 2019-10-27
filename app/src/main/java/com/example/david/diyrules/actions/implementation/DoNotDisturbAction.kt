package com.example.david.diyrules.actions.implementation

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.example.david.diyrules.AutoMate
import com.example.david.diyrules.actions.interfaces.DoNotDisturbAction
import com.example.david.diyrules.mediator.PermissionMediator

abstract class DoNotDisturbAction() : DoNotDisturbAction {

    protected val notificationManager: NotificationManager = AutoMate.access().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun execute() {
        // 1) see if we are allowed notification wise to access the notification policy
        if(ContextCompat.checkSelfPermission(AutoMate.access().applicationContext, Manifest.permission.ACCESS_NOTIFICATION_POLICY) == PackageManager.PERMISSION_GRANTED) {
            //2) permission is formally granted: good
            // no we need to check if we can access it as well
            if (notificationManager.isNotificationPolicyAccessGranted) {
                // we are allowed to change the notification policy
                setDoNotDisturb()

            } else {
                // we are NOT allowed to change the notification policy
                // to remedy this: show the user a dialog to tell her WHY we need this permission
                // and that she has to enable it in the settings
                // then open the settings app
                // TODO: handle no settings app present
                // TODO: handle managed (=work) account on device
                PermissionMediator.requestSettingsApp(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            }

        } else {
            // we do not have permission to access the notification policy
            // request permission
            PermissionMediator.requestPermission(arrayOf(Manifest.permission.ACCESS_NOTIFICATION_POLICY), ::permissionCallback)
        }
    }

    private fun permissionCallback(permissions: Array<out String>, grantResults: IntArray) {
        //TODO: find out WTH I was planning to do here
        if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //permission was granted -> noice
            Toast.makeText(AutoMate.access(), "permission to access notification policy was granted! yay", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(AutoMate.access(), "permission to access notification policy was denied! boo", Toast.LENGTH_SHORT).show()
        }
        return
    }

    abstract fun setDoNotDisturb()
}