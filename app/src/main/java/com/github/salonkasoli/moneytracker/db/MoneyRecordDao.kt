package com.github.salonkasoli.moneytracker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MoneyRecordDao {
    @Insert
    fun insert(entity: MoneyRecordEntity) : Completable

    @Query("SELECT * FROM MoneyRecordEntity ORDER BY id DESC LIMIT 1")
    fun getLast() : Single<MoneyRecordEntity>
}