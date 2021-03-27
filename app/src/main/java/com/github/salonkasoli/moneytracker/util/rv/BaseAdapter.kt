package com.github.salonkasoli.moneytracker.util.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items: MutableList<Any> = mutableListOf()

    private val delegates: MutableList<AdapterDelegate> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegates[viewType].createHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        findDelegate(position).bind(holder, position, items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return delegates.indexOfFirst {
            it.isForViewType(position, items[position])
        }
    }

    fun addDelegate(delegate: AdapterDelegate) {
        delegates.add(delegate)
    }

    private fun findDelegate(position: Int): AdapterDelegate {
        return delegates.find { it.isForViewType(position, items[position]) }!!
    }
}