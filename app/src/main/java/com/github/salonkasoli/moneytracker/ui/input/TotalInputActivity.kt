package com.github.salonkasoli.moneytracker.ui.input

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.salonkasoli.moneytracker.databinding.ActivityTotalInputBinding
import com.github.salonkasoli.moneytracker.util.State
import com.github.salonkasoli.moneytracker.util.StateController

class TotalInputActivity : AppCompatActivity() {

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, TotalInputActivity::class.java)
        }
    }

    private val viewModel: TotalInputViewModel by viewModels()
    private lateinit var binding: ActivityTotalInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTotalInputBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val stateController = StateController.default(binding.root)
        viewModel.state.observe(this) {
            stateController.setState(it)
            if (it is State.Content) {
                finish()
            }
        }

        binding.buttonSave.setOnClickListener {
            viewModel.saveNewTotal(binding.editText.editableText.toString().toInt())
        }
    }
}