package com.github.salonkasoli.moneytracker.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MoneyPartEntity(
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "value")
    val value: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}