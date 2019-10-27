package com.example.david.diyrules.actions.implementation

import com.example.david.diyrules.actions.DoNotDisturbLevel

object EnableDoNotDisturbAction : DoNotDisturbAction() {

    var level: DoNotDisturbLevel = DoNotDisturbLevel.TOTAL_SILENCE

    override fun setDoNotDisturb() {
        notificationManager.setInterruptionFilter(level.systemCode)
    }

}