package com.example.david.diyrules.rules.interfaces

import com.example.david.diyrules.actions.interfaces.Action
import com.example.david.diyrules.triggers.interfaces.TriggerEvent

interface Rule {
    val trigger: TriggerEvent
    val action: Action
    var isActive: Boolean
}