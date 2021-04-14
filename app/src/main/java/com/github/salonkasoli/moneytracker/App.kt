package com.github.salonkasoli.moneytracker

import android.app.Application
import com.github.salonkasoli.moneytracker.di.AppComponent
import com.github.salonkasoli.moneytracker.di.DaggerAppComponent
import com.github.salonkasoli.moneytracker.di.module.DatabaseModule

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(this))
            .build()
    }
}