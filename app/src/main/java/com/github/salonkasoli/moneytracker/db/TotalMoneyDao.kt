package com.github.salonkasoli.moneytracker.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TotalMoneyDao {
    @Query("SELECT * FROM totalmoneyentity")
    fun getTotal(): Single<List<TotalMoneyEntity>>

    @Delete
    fun delete(entity: TotalMoneyEntity): Completable

    @Insert
    fun insert(entity: TotalMoneyEntity): Completable
}