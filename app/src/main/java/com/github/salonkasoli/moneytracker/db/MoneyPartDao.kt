package com.github.salonkasoli.moneytracker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MoneyPartDao {

    @Insert
    fun insert(entity: MoneyPartEntity): Completable

    @Query("SELECT * FROM MoneyPartEntity WHERE date = :date")
    fun findByDate(date: Long): Single<List<MoneyPartEntity>>
}