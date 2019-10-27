package com.example.david.diyrules.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.example.david.diyrules.mediator.PermissionMediator

/**
 * base activity for handling permissions
 */
abstract class AutoMateActivity: AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        PermissionMediator.currentActivity = this
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        PermissionMediator.permissionResult(requestCode, permissions, grantResults)
    }

}