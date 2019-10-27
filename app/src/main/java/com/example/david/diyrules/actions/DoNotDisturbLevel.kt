package com.example.david.diyrules.actions

import android.app.NotificationManager
import com.example.david.diyrules.R

enum class DoNotDisturbLevel(val systemCode: Int, val representationStringResource: Int) {
    TOTAL_SILENCE(NotificationManager.INTERRUPTION_FILTER_NONE, R.string.do_not_disturb_level_total_silence),
    ALARMS_ONLY(NotificationManager.INTERRUPTION_FILTER_ALARMS, R.string.do_not_disturb_level_alarms_only),
    PRIORITY_ONLY(NotificationManager.INTERRUPTION_FILTER_PRIORITY, R.string.do_not_disturb_level_priority_only),
    OFF(NotificationManager.INTERRUPTION_FILTER_ALL, R.string.do_not_disturb_level_off)
}