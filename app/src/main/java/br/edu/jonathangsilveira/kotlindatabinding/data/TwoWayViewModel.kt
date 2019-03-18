package br.edu.jonathangsilveira.kotlindatabinding.data

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import br.edu.jonathangsilveira.kotlindatabinding.model.Transaction

class TwoWayViewModel(application: Application) : AndroidViewModel(application) {

    val repo = FakeRepository

    val value: MutableLiveData<Double> by lazy { MutableLiveData<Double>().apply { value = 10.8 } }

    val isShowingValues: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply {
            value = false
        }
    }

    val paymentMethod: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            value = "debit"
        }
    }

    val transactions: MutableLiveData<List<Transaction>> by lazy { MutableLiveData<List<Transaction>>() }

    val isDebitSelected: Boolean
        get() = paymentMethod.value == "debit"

    val isCreditSelected: Boolean
        get() = paymentMethod.value == "credit"

    fun onShowValuesChanged(isChecked: Boolean) {
        Log.d(TAG, "onShowValuesChanged(Boolean) -> $isChecked")
    }

    fun onPaymentMethodSelected(paymentMethod: String?) {
        this.paymentMethod.value = paymentMethod
        filter()
    }

    fun onValueChanged(newValue: Double) {
        value.value = newValue
        filter()
    }

    private fun filter() {
        transactions.value = repo.filter(
            value = value.value ?: 0.0,
            method = if (paymentMethod.value == "debit") 1 else 2
        )
    }

    companion object {

        private const val TAG = "TwoWayViewModel"

    }

}