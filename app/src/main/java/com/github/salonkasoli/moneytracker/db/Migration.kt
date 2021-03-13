package com.github.salonkasoli.moneytracker.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1to2 : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE TotalMoneyEntity ADD COLUMN  date INTEGER NOT NULL DEFAULT 0")
    }
}