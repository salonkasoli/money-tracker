package com.github.salonkasoli.moneytracker.ui.moneypart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.salonkasoli.moneytracker.App
import com.github.salonkasoli.moneytracker.domain.EditMoneyRepository
import com.github.salonkasoli.moneytracker.ui.moneypart.rv.MoneyPartAddItem
import com.github.salonkasoli.moneytracker.ui.moneypart.rv.MoneyPartItem
import com.github.salonkasoli.moneytracker.ui.moneypart.rv.MoneyPartTotalItem
import com.github.salonkasoli.moneytracker.util.AppLogger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoneyPartViewModel : ViewModel() {

    @Inject
    lateinit var repo: EditMoneyRepository

    val state: LiveData<MoneyPartViewState>
        get() = _state

    private val _state = MutableLiveData(MoneyPartViewState(emptyList()))
    private val disposable = CompositeDisposable()

    init {
        App.instance.appComponent.inject(this)
    }

    fun loadData() {
        disposable.add(
            repo.getOrCreate()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        val resultList = mutableListOf<Any>()
                        resultList.add(MoneyPartTotalItem(
                            it.sumOf { it.value }
                        ))
                        resultList.addAll(
                            it.mapIndexed { index, editMoneyEntity ->
                                MoneyPartItem(
                                    editMoneyEntity.name,
                                    editMoneyEntity.value.toString(),
                                    index
                                )
                            }
                        )
                        resultList.add(MoneyPartAddItem())
                        _state.postValue(
                            MoneyPartViewState(resultList)
                        )
                    },
                    {
                        AppLogger.log("MoneyPartViewModel getting error $it")
                    }
                )
        )
    }

    fun save() {
        disposable.add(
            repo.save()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        AppLogger.log("save success")
                        _state.postValue(MoneyPartViewState(emptyList(), true))
                    },
                    {
                        AppLogger.log("MoneyPartViewModel saving error $it")
                    }
                )
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}