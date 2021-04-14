package com.github.salonkasoli.moneytracker.di.module

import android.content.Context
import androidx.room.Room
import com.github.salonkasoli.moneytracker.db.AppDatabase
import com.github.salonkasoli.moneytracker.db.MoneyPartDao
import com.github.salonkasoli.moneytracker.db.MoneyRecordDao
import com.github.salonkasoli.moneytracker.db.TotalMoneyDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(
    private val context: Context
) {

    companion object {
        private const val DB_NAME = "app_database"
    }

    @Provides
    @Singleton
    fun database(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun moneyRecordDao(db: AppDatabase): MoneyRecordDao {
        return db.moneyRecordDao()
    }

    @Provides
    fun moneyPartDao(db: AppDatabase): MoneyPartDao {
        return db.moneyPartDao()
    }

    @Provides
    fun totalMoneyDao(db: AppDatabase): TotalMoneyDao {
        return db.totalMoneyDao()
    }
}