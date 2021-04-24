package com.github.salonkasoli.moneytracker.ui.main

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.salonkasoli.moneytracker.App
import com.github.salonkasoli.moneytracker.db.TotalMoneyDao
import com.github.salonkasoli.moneytracker.db.TotalMoneyEntity
import com.github.salonkasoli.moneytracker.domain.MoneyRecordRepository
import com.github.salonkasoli.moneytracker.ui.common.Segment
import com.github.salonkasoli.moneytracker.util.AppLogger
import com.github.salonkasoli.moneytracker.util.TimeUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.random.Random

class MainViewModel : ViewModel() {

    @Inject
    lateinit var repo: MoneyRecordRepository

    val data: LiveData<MainViewState>
        get() = _data

    private val _data = MutableLiveData<MainViewState>()
    private val disposable = CompositeDisposable()

    init {
        App.instance.appComponent.inject(this)
    }

    fun updateData() {
        disposable.add(
            repo.getLast()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        _data.postValue(
                            MainViewState(
                                totalText = it.sumBy { it.value.toInt() }.toString(),
                                segments = it.map {
                                    Segment(Random.nextInt())
                                }
                            )
                        )
                    },
                    {
                        _data.postValue(
                            MainViewState(
                                "0",
                                emptyList()
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