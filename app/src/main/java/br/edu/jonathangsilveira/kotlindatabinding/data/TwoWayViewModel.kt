package br.edu.jonathangsilveira.kotlindatabinding.data

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log

class TwoWayViewModel(application: Application) : AndroidViewModel(application) {

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

    val isDebitSelected: Boolean
        get() = paymentMethod.value == "debit"

    val isCreditSelected: Boolean
        get() = paymentMethod.value == "credit"

    fun onShowValuesChanged(isChecked: Boolean) {
        Log.d(TAG, "onShowValuesChanged(Boolean) -> $isChecked")
    }

    fun onPaymentoMethodSelected(paymentMethod: String?) {
        this.paymentMethod.value = paymentMethod
    }

    companion object {

        private const val TAG = "TwoWayViewModel"

    }

}