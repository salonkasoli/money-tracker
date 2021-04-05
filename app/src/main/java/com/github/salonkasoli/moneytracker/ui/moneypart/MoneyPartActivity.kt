package com.github.salonkasoli.moneytracker.ui.moneypart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.salonkasoli.moneytracker.databinding.ActivityMoneyPartBinding
import com.github.salonkasoli.moneytracker.ui.input.TotalInputActivity
import com.github.salonkasoli.moneytracker.ui.input.TotalInputArgs
import com.github.salonkasoli.moneytracker.ui.moneypart.rv.MoneyPartAddDelegate
import com.github.salonkasoli.moneytracker.ui.moneypart.rv.MoneyPartItemDelegate
import com.github.salonkasoli.moneytracker.ui.moneypart.rv.MoneyPartTotalDelegate
import com.github.salonkasoli.moneytracker.util.rv.BaseAdapter

class MoneyPartActivity : AppCompatActivity() {

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, MoneyPartActivity::class.java)
        }
    }

    private val viewModel: MoneyPartViewModel by viewModels()

    private lateinit var binding: ActivityMoneyPartBinding
    private val adapter = BaseAdapter().apply {
        addDelegate(MoneyPartTotalDelegate())
        addDelegate(MoneyPartItemDelegate {
            startActivity(
                TotalInputActivity.intent(
                    this@MoneyPartActivity,
                    TotalInputArgs(
                        it.index,
                        it.title,
                        it.total.toLong()
                    )
                )
            )
        })
        addDelegate(MoneyPartAddDelegate {
            startActivity(TotalInputActivity.intent(this@MoneyPartActivity, TotalInputArgs(-1)))
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoneyPartBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.list.adapter = adapter
        binding.buttonSave.setOnClickListener {
            viewModel.save()
        }
        viewModel.state.observe(this) {
            if (it.isFinished) {
                finish()
            }
            adapter.items.clear()
            adapter.items.addAll(it.data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }
}