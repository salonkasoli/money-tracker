package com.github.salonkasoli.moneytracker.domain

import com.github.salonkasoli.moneytracker.db.MoneyPartEntity
import com.github.salonkasoli.moneytracker.util.AppLogger
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditMoneyRepository @Inject constructor(
    private val moneyRecordRepository: MoneyRecordRepository
) {

    private var draft: List<EditMoneyEntity>? = null

    fun getOrCreate(): Single<List<EditMoneyEntity>> {
        draft?.let {
            return Single.just(it)
        }

        return moneyRecordRepository.getLast()
            .map { list: List<MoneyPartEntity> ->
                list.map { EditMoneyEntity(it.name, it.value) }
            }
            .onErrorReturn { error -> emptyList() }
            .doOnSuccess { draft = it }
    }

    fun update(newDraft: List<EditMoneyEntity>): Completable {
        return Completable.fromAction {
            draft = newDraft
        }
    }

    fun save(): Completable {
        val date = System.currentTimeMillis()
        return Single.just(draft!!)
            .map { draft ->
                draft.map { MoneyPartEntity(date, it.name, it.value) }
            }.flatMapCompletable {
                moneyRecordRepository.add(it, date)
            }
            .andThen(Completable.fromAction { draft = null })
    }
}

class EditMoneyEntity(
    val name: String,
    val value: Long
)