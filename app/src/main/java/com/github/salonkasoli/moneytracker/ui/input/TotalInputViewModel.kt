package com.github.salonkasoli.moneytracker.ui.input

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.salonkasoli.moneytracker.App
import com.github.salonkasoli.moneytracker.db.TotalMoneyDao
import com.github.salonkasoli.moneytracker.db.TotalMoneyEntity
import com.github.salonkasoli.moneytracker.util.AppLogger
import com.github.salonkasoli.moneytracker.util.State
import com.github.salonkasoli.moneytracker.util.TimeUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TotalInputViewModel : ViewModel() {

    val state: LiveData<State>
        get() = _state

    private val _state = MutableLiveData<State>()

    private val db: TotalMoneyDao = App.instance.db.totalMoneyDao()
    private val disposable = CompositeDisposable()

    fun saveNewTotal(newValue: Int) {
        disposable.add(
            db.insert(TotalMoneyEntity(newValue, TimeUtil.currentTime()))
                .doOnSubscribe { _state.postValue(State.Loading) }
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        _state.postValue(State.Content())
                    },
                    {
                        _state.postValue(State.Error(it))
                        AppLogger.log("Error while saving total. Error = $it")
                    }
                )
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}