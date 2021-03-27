package com.github.salonkasoli.moneytracker

import android.app.Application
import androidx.room.Room
import com.github.salonkasoli.moneytracker.db.AppDatabase

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var db: AppDatabase

    override fun onCreate() {
        instance = this
        super.onCreate()
        initDatabase()
    }

    private fun initDatabase() {
        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
}