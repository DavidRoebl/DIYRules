package com.example.david.diyrules

object ReceiverMediator {

    private val consumers: HashMap<RuleEvent, MutableSet<EventConsumer>> = HashMap()

    public fun onEvent(event: RuleEvent){
        consumers[event]?.forEach { it.onEvent() }
    }

    public fun subscribe(eventConsumer: EventConsumer, vararg events: RuleEvent){
        events.forEach {
            if(null == consumers[it]){
                consumers[it] = mutableSetOf(eventConsumer)
            } else {
                consumers[it]!!.add(eventConsumer)
            }
        }
    }

}