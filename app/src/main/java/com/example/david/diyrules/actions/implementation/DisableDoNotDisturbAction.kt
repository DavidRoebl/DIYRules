package com.example.david.diyrules.actions.implementation

import com.example.david.diyrules.actions.DoNotDisturbLevel


object DisableDoNotDisturbAction: DoNotDisturbAction() {

    override fun setDoNotDisturb() {
        notificationManager.setInterruptionFilter(DoNotDisturbLevel.OFF.systemCode)
    }
}