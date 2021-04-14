package com.github.salonkasoli.moneytracker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.salonkasoli.moneytracker.App
import com.github.salonkasoli.moneytracker.db.TotalMoneyDao
import com.github.salonkasoli.moneytracker.db.TotalMoneyEntity
import com.github.salonkasoli.moneytracker.util.AppLogger
import com.github.salonkasoli.moneytracker.util.TimeUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var db: TotalMoneyDao

    val data: LiveData<MainViewState>
        get() = _data

    private val _data = MutableLiveData<MainViewState>()
    private val disposable = CompositeDisposable()

    init {
        App.instance.appComponent.inject(this)
    }

    fun updateData() {
        disposable.add(
            db.getTotal()
                .map { it.last() }
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        _data.postValue(MainViewState(it))
                    },
                    {
                        _data.postValue(
                            MainViewState(
                                TotalMoneyEntity(
                                    0,
                                    TimeUtil.currentTime()
                                )
                            )
                        )
                        AppLogger.log("Error while getting total from db. Error = $it")
                    }
                )
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}