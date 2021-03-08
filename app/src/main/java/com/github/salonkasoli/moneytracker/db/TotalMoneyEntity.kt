package com.github.salonkasoli.moneytracker.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TotalMoneyEntity(
    @ColumnInfo(name = "total_money")
    val totalMoney: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}