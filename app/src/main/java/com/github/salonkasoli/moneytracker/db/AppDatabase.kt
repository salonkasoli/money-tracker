package com.github.salonkasoli.moneytracker.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TotalMoneyEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun totalMoneyDao(): TotalMoneyDao
}