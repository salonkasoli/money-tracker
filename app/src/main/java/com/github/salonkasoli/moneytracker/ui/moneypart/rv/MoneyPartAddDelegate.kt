package com.github.salonkasoli.moneytracker.ui.moneypart.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.salonkasoli.moneytracker.databinding.MoneyPartAddBinding
import com.github.salonkasoli.moneytracker.util.rv.AdapterDelegate

class MoneyPartAddDelegate(
    private val clickListener: () -> Unit
) : AdapterDelegate {

    override fun isForViewType(position: Int, item: Any) = item is MoneyPartAddItem

    override fun bind(holder: RecyclerView.ViewHolder, position: Int, item: Any) {
        holder.itemView.setOnClickListener {
            clickListener.invoke()
        }
    }

    override fun createHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return Holder(
            MoneyPartAddBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class Holder(val binding: MoneyPartAddBinding) : RecyclerView.ViewHolder(binding.root)
}

class MoneyPartAddItem