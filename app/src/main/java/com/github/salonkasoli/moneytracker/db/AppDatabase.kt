package com.github.salonkasoli.moneytracker.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TotalMoneyEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun totalMoneyDao(): TotalMoneyDao
}