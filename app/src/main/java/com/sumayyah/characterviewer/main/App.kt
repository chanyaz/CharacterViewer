package com.sumayyah.characterviewer.main

import android.app.Application
import android.content.Context

/**
 * Created by sahmed014c on 11/17/17.
 */
class App : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}