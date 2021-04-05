package com.github.salonkasoli.moneytracker

import android.app.Application
import androidx.room.Room
import com.github.salonkasoli.moneytracker.db.AppDatabase
import com.github.salonkasoli.moneytracker.domain.EditMoneyRepository
import com.github.salonkasoli.moneytracker.domain.MoneyRecordRepository

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var db: AppDatabase

    val editMoneyRepository: EditMoneyRepository by lazy {
        EditMoneyRepository(
            MoneyRecordRepository(
                db.moneyRecordDao(),
                db.moneyPartDao()
            )
        )
    }

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