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
        private const val EXTRA_ARGS = "extra_args"

        fun intent(context: Context, args: TotalInputArgs): Intent {
            return Intent(context, TotalInputActivity::class.java).apply {
                putExtra(EXTRA_ARGS, args)
            }
        }
    }

    private val args: TotalInputArgs
        get() = intent.getSerializableExtra(EXTRA_ARGS) as TotalInputArgs

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

        binding.totalEditText.setText(args.total.toString())
        binding.nameEditText.setText(args.name)

        binding.buttonSave.setOnClickListener {
            viewModel.update(
                TotalInputArgs(
                    args.index,
                    binding.nameEditText.editableText.toString(),
                    binding.totalEditText.editableText.toString().toLong()
                )
            )
        }
    }
}