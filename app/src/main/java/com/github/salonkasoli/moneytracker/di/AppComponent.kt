package com.github.salonkasoli.moneytracker.di

import com.github.salonkasoli.moneytracker.di.module.DatabaseModule
import com.github.salonkasoli.moneytracker.ui.input.TotalInputViewModel
import com.github.salonkasoli.moneytracker.ui.main.MainViewModel
import com.github.salonkasoli.moneytracker.ui.moneypart.MoneyPartViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DatabaseModule::class]
)
interface AppComponent  {

    fun inject(model: MainViewModel)
    fun inject(model: TotalInputViewModel)
    fun inject(model: MoneyPartViewModel)
}