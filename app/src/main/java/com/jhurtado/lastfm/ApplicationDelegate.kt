package com.jhurtado.lastfm

import android.app.Application
import android.content.Context

class ApplicationDelegate : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = applicationContext()
    }

    companion object {
        var instance: ApplicationDelegate? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}