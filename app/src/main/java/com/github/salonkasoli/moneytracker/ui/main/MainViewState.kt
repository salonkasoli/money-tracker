package com.github.salonkasoli.moneytracker.ui.main

import com.github.salonkasoli.moneytracker.db.TotalMoneyEntity
import com.github.salonkasoli.moneytracker.ui.common.Segment

class MainViewState(
    val totalText: String,
    val segments: List<Segment>
)