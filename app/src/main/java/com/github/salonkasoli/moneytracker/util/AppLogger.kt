package com.github.salonkasoli.moneytracker.util

import android.util.Log
import com.github.salonkasoli.moneytracker.BuildConfig

object AppLogger {

    private const val TAG = "MoneyTracker"

    fun log(message: String) {
        if (BuildConfig.DEBUG) {
            Log.wtf(TAG, message)
        }
    }
}