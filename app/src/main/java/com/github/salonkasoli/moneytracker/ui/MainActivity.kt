package com.github.salonkasoli.moneytracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.salonkasoli.moneytracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel.data.observe(this) {
            binding.text.text = "У вас всего ${it.totalMoneyEntity.totalMoney} рублей"
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateData()
    }
}