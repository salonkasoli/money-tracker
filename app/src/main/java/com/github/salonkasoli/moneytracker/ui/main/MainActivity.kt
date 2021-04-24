package com.github.salonkasoli.moneytracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.salonkasoli.moneytracker.databinding.ActivityMainBinding
import com.github.salonkasoli.moneytracker.ui.main.rv.CircleChartAdapter
import com.github.salonkasoli.moneytracker.ui.main.rv.CircleChartItem
import com.github.salonkasoli.moneytracker.ui.moneypart.MoneyPartActivity
import com.github.salonkasoli.moneytracker.util.rv.BaseAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private val adapter = BaseAdapter().apply {
        addDelegate(CircleChartAdapter())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(this)

        viewModel.data.observe(this) {
            adapter.items.clear()
            adapter.items.add(
                CircleChartItem(
                    it.segments,
                    it.totalText
                )
            )
            adapter.notifyDataSetChanged()
        }

        binding.buttonAdd.setOnClickListener {
            startActivity(MoneyPartActivity.newIntent(this))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateData()
    }
}