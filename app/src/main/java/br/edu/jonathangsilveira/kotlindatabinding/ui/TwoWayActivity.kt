package br.edu.jonathangsilveira.kotlindatabinding.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.RadioButton
import br.edu.jonathangsilveira.kotlindatabinding.R
import br.edu.jonathangsilveira.kotlindatabinding.adapter.TransactionAdapter
import br.edu.jonathangsilveira.kotlindatabinding.data.TwoWayViewModel
import br.edu.jonathangsilveira.kotlindatabinding.databinding.TwoWayBinding
import br.edu.jonathangsilveira.kotlindatabinding.model.Transaction

class TwoWayActivity : AppCompatActivity() {

    private val adapter: TransactionAdapter by lazy { TransactionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<TwoWayBinding>(this, R.layout.two_way).apply {
            lifecycleOwner = this@TwoWayActivity
            viewModel = ViewModelProviders.of(this@TwoWayActivity).get(TwoWayViewModel::class.java)
            transactions.setup {
                adapter = this@TwoWayActivity.adapter
                layoutManager = LinearLayoutManager(
                    this@TwoWayActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
            paymentMethods.setOnCheckedChangeListener { view, checkedId ->
                this.viewModel?.onPaymentMethodSelected(view.findViewById<RadioButton>(checkedId)?.tag?.toString())
            }
            value.setAfterTextChangedListener { newValue ->
                viewModel?.value?.let { property ->
                    if (newValue != property.value)
                        viewModel?.onValueChanged(newValue)
                }
            }
        }.also { binding ->
            binding.viewModel?.transactions?.observe(this, Observer<List<Transaction>> { transactions ->
                adapter.updateData(transactions)
            })
        }
    }

    private fun RecyclerView.setup(action: RecyclerView.() -> Unit): RecyclerView {
        action(this)
        return this
    }

}
