package com.github.salonkasoli.moneytracker.ui.moneypart.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.salonkasoli.moneytracker.databinding.MoneyPartListItemBinding
import com.github.salonkasoli.moneytracker.util.rv.AdapterDelegate

class MoneyPartItemDelegate : AdapterDelegate {

    override fun isForViewType(position: Int, item: Any) = item is MoneyPartItem

    override fun bind(holder: RecyclerView.ViewHolder, position: Int, item: Any) {
        holder as Holder
        item as MoneyPartItem
        holder.binding.title.text = item.title
        holder.binding.total.text = item.total
    }

    override fun createHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return Holder(
            MoneyPartListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class Holder(val binding: MoneyPartListItemBinding) : RecyclerView.ViewHolder(binding.root)
}

class MoneyPartItem(
    val title: String,
    val total: String
)