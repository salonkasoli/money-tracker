package com.github.salonkasoli.moneytracker.domain

import com.github.salonkasoli.moneytracker.db.MoneyPartDao
import com.github.salonkasoli.moneytracker.db.MoneyPartEntity
import com.github.salonkasoli.moneytracker.db.MoneyRecordDao
import com.github.salonkasoli.moneytracker.db.MoneyRecordEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class MoneyRecordRepository(
    private val moneyRecordDao: MoneyRecordDao,
    private val moneyPartDao: MoneyPartDao
) {

    fun getLast(): Single<List<MoneyPartEntity>> {
        return moneyRecordDao.getLast()
            .flatMap { moneyPartDao.findByDate(it.date) }
    }

    // TODO do it via 1 transaction
    fun add(list: List<MoneyPartEntity>, date: Long): Completable {
        return moneyRecordDao.insert(MoneyRecordEntity(date))
            .andThen(
                Observable.fromIterable(list)
                    .flatMapCompletable { entity ->
                        moneyPartDao.insert(entity)
                    }
            )
    }
}