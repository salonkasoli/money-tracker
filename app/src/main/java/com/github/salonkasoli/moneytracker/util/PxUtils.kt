package com.github.salonkasoli.moneytracker.util

import android.util.TypedValue
import com.github.salonkasoli.moneytracker.App

object PxUtils {

    fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            App.instance.resources.displayMetrics
        )
    }
}