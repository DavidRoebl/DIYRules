package com.example.david.diyrules.mediator

import com.example.david.diyrules.triggers.interfaces.TriggerEvent

object EventMediator {

    private val CONSUMERS: HashMap<TriggerEvent, MutableSet<EventConsumer>> = HashMap()

    public fun onEvent(event: TriggerEvent){
        CONSUMERS[event]?.forEach { it.onEvent() }
    }

    fun unsubscribe(eventConsumer: EventConsumer, vararg events: TriggerEvent){
        events.forEach{
            CONSUMERS[it]?.remove(eventConsumer)
        }
    }

    public fun subscribe(eventConsumer: EventConsumer, vararg events: TriggerEvent){
        events.forEach {
            if(null == CONSUMERS[it]){
                CONSUMERS[it] = mutableSetOf(eventConsumer)
            } else {
                CONSUMERS[it]!!.add(eventConsumer)
            }
        }
    }

}