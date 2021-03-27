package com.github.salonkasoli.moneytracker.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TotalMoneyEntity::class, MoneyPartEntity::class, MoneyRecordEntity::class],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun totalMoneyDao(): TotalMoneyDao
    abstract fun moneyPartDao(): MoneyPartDao
    abstract fun moneyRecordDao(): MoneyRecordDao
}