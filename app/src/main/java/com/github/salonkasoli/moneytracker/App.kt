package com.github.salonkasoli.moneytracker

import android.app.Application
import androidx.room.Room
import com.github.salonkasoli.moneytracker.db.AppDatabase
import com.github.salonkasoli.moneytracker.db.Migration1to2

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
        ).addMigrations(Migration1to2())
            .build()
    }
}