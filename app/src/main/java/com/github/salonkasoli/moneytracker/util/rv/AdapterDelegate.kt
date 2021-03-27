package com.github.salonkasoli.moneytracker.util.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface AdapterDelegate {
    fun isForViewType(position: Int, item: Any): Boolean

    fun bind(holder: RecyclerView.ViewHolder, position: Int, item: Any)

    fun createHolder(parent: ViewGroup) : RecyclerView.ViewHolder
}