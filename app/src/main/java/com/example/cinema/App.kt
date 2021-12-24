package com.example.cinema

import android.app.Application
import android.content.Context
import com.example.cinema.core.AppComponent
import com.example.cinema.core.DaggerAppComponent

class App: Application() {

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        component = DaggerAppComponent.builder()
            .build()

    }

    fun getAppComponent(): AppComponent {
        return component
    }

    companion object {

        private fun getApp(context: Context): App {
            return context.applicationContext as App
        }

        lateinit var INSTANCE: App
    }
}