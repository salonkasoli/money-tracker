package com.github.salonkasoli.moneytracker.ui.input

import java.io.Serializable

class TotalInputArgs(
    val index: Int,
    val name: String = "",
    val total: Long = 0
) : Serializable