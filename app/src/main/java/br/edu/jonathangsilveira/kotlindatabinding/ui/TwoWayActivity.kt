package br.edu.jonathangsilveira.kotlindatabinding.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioButton
import br.edu.jonathangsilveira.kotlindatabinding.R
import br.edu.jonathangsilveira.kotlindatabinding.data.TwoWayViewModel
import br.edu.jonathangsilveira.kotlindatabinding.databinding.TwoWayBinding

class TwoWayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<TwoWayBinding>(this, R.layout.two_way).apply {
            viewModel = ViewModelProviders.of(this@TwoWayActivity).get(TwoWayViewModel::class.java)
            lifecycleOwner = this@TwoWayActivity
            paymentMethods.setOnCheckedChangeListener { view, checkedId ->
                this.viewModel?.onPaymentMethodSelected(view.findViewById<RadioButton>(checkedId)?.tag?.toString())
            }
            value.setAfterTextChangedListener { newValue ->
                viewModel?.value?.let { property ->
                    if (newValue != property.value)
                        viewModel?.onValueChanged(newValue)
                }
            }
        }
    }

}
