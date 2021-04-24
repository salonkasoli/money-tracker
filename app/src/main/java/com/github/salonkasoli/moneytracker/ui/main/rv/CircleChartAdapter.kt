package com.github.salonkasoli.moneytracker.ui.main.rv

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.salonkasoli.moneytracker.databinding.MainCircleChartBinding
import com.github.salonkasoli.moneytracker.ui.common.Segment
import com.github.salonkasoli.moneytracker.util.rv.AdapterDelegate

class CircleChartAdapter : AdapterDelegate {

    override fun isForViewType(position: Int, item: Any) = item is CircleChartItem

    override fun bind(holder: RecyclerView.ViewHolder, position: Int, item: Any) {
        holder as Holder
        item as CircleChartItem
        holder.binding.circleChart.setSegments(item.segments)
        holder.binding.totalText.text = item.totalText
        Log.wtf("lol", "bind is finished")
    }

    override fun createHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return Holder(
            MainCircleChartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class Holder(
        val binding: MainCircleChartBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

class CircleChartItem(
    val segments: List<Segment>,
    val totalText: String
)