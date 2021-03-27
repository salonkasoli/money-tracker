package com.github.salonkasoli.moneytracker.ui.moneypart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.salonkasoli.moneytracker.App
import com.github.salonkasoli.moneytracker.domain.MoneyRecordRepository
import com.github.salonkasoli.moneytracker.ui.moneypart.rv.MoneyPartItem
import com.github.salonkasoli.moneytracker.util.AppLogger
import io.reactivex.schedulers.Schedulers

class MoneyPartViewModel : ViewModel() {

    val state: LiveData<MoneyPartViewState>
        get() = _state

    private val repo = MoneyRecordRepository(
        App.instance.db.moneyRecordDao(),
        App.instance.db.moneyPartDao()
    )
    private val _state = MutableLiveData(MoneyPartViewState(emptyList()))

    fun loadData() {
        repo.getLast()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    _state.postValue(
                        MoneyPartViewState(
                            it.map {
                                MoneyPartItem(
                                    it.name,
                                    it.value.toString()
                                )
                            }
                        )
                    )
                },
                {
                    AppLogger.log("MoneyPartViewModel error $it")
                }
            )
    }

}