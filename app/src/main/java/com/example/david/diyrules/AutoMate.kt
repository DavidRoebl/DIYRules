package com.example.david.diyrules

import android.app.Application
import android.content.Context

class AutoMate: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: AutoMate? = null

        fun access(): AutoMate{
            return instance!! //todo: find way to remove non-null assertion (?!)
        }
    }
}