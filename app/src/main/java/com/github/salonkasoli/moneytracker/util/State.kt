package com.github.salonkasoli.moneytracker.util

sealed class State {
    object Empty: State()
    object Loading : State()
    class Error(val cause: Throwable) : State()
    class Content(val data: Any? = null): State()
}