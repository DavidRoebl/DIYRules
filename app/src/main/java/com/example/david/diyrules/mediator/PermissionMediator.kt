package com.example.david.diyrules.mediator

import android.content.Intent
import android.util.Log
import com.example.david.diyrules.activities.AutoMateActivity

object PermissionMediator {
    const val TAG: String = "PermissionMediator"
    var resultCodeCounter: Int = 0

    private val permissionCallbacks : MutableMap<Int, (permissions: Array<out String>, grantResults: IntArray) -> Unit > = HashMap()

    var currentActivity: AutoMateActivity? = null //TODO: find better way for initialization

    fun requestPermission(permission: Array<String>,
                          onPermissionCallback : (permissions: Array<out String>, grantResults: IntArray) -> Unit){

        val resultCode = resultCodeCounter++
        permissionCallbacks[resultCode] = onPermissionCallback
        currentActivity?.requestPermissions(permission, resultCode)
    }

    fun permissionResult(resultCode: Int, permissions: Array<out String>, grantResults: IntArray){
        Log.d(TAG, "handling permission result for code: $resultCode")
        permissionCallbacks[resultCode]?.invoke(permissions, grantResults)
        permissionCallbacks.remove(resultCode)
    }

    fun requestSettingsApp(settings: String){
        currentActivity?.startActivityForResult(Intent(settings), 0)
    }
}