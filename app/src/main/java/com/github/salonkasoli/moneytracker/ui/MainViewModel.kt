package com.github.salonkasoli.moneytracker.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.salonkasoli.moneytracker.App
import com.github.salonkasoli.moneytracker.db.TotalMoneyDao
import com.github.salonkasoli.moneytracker.db.TotalMoneyEntity
import com.github.salonkasoli.moneytracker.util.AppLogger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.logging.Logger

class MainViewModel : ViewModel() {

    val data: LiveData<MainViewState>
        get() = _data

    private val _data = MutableLiveData<MainViewState>()

    private val db: TotalMoneyDao = App.instance.db.totalMoneyDao()
    private val disposable = CompositeDisposable()

    fun updateData() {
        disposable.add(
            db.getTotal()
                .map { it[0] }
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        _data.postValue(MainViewState(it))
                    },
                    {
                        _data.postValue(MainViewState(TotalMoneyEntity(0)))
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