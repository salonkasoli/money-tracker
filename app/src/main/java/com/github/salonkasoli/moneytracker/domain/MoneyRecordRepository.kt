package com.github.salonkasoli.moneytracker.domain

import com.github.salonkasoli.moneytracker.db.MoneyPartDao
import com.github.salonkasoli.moneytracker.db.MoneyPartEntity
import com.github.salonkasoli.moneytracker.db.MoneyRecordDao
import io.reactivex.Single

class MoneyRecordRepository(
    private val moneyRecordDao: MoneyRecordDao,
    private val moneyPartDao: MoneyPartDao
) {

    fun getLast(): Single<List<MoneyPartEntity>> {
        return moneyRecordDao.getLast()
            .flatMap { moneyPartDao.findByDate(it.date) }
    }
}