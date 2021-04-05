package com.github.salonkasoli.moneytracker.ui.moneypart.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.salonkasoli.moneytracker.databinding.MoneyPartTotalBinding
import com.github.salonkasoli.moneytracker.util.rv.AdapterDelegate

class MoneyPartTotalDelegate : AdapterDelegate {

    override fun isForViewType(position: Int, item: Any) = item is MoneyPartTotalItem

    override fun bind(holder: RecyclerView.ViewHolder, position: Int, item: Any) {
        item as MoneyPartTotalItem
        holder as Holder
        holder.binding.total.text = item.total.toString()
    }

    override fun createHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return Holder(
            MoneyPartTotalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class Holder(
        val binding: MoneyPartTotalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }
}

class MoneyPartTotalItem(
    val total: Long
)