package com.example.david.diyrules.rules.implementation

import com.example.david.diyrules.actions.interfaces.Action
import com.example.david.diyrules.mediator.EventConsumer
import com.example.david.diyrules.mediator.EventMediator
import com.example.david.diyrules.rules.interfaces.Rule
import com.example.david.diyrules.triggers.interfaces.TriggerEvent

class BasicRule(override val trigger: TriggerEvent,
                override val action: Action) : Rule {

    override var isActive = false
        set(value) {
            field = value

            if(value){
                EventMediator.subscribe(eventConsumer, trigger)
            } else {
                EventMediator.unsubscribe(eventConsumer, trigger)
            }
        }

    private val eventConsumer: EventConsumer = EventConsumer { action.execute() }

}