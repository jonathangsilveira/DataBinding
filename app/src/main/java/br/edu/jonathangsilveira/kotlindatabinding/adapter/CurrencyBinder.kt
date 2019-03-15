package br.edu.jonathangsilveira.kotlindatabinding.adapter

import android.arch.lifecycle.MutableLiveData
import android.databinding.*
import android.util.Log
import br.edu.jonathangsilveira.kotlindatabinding.ui.MoneyTextInputEditText

@InverseBindingMethods(
    InverseBindingMethod(
        type = MoneyTextInputEditText::class,
        attribute = "app:value",
        method = "getValue"
    )
)
class CurrencyBinder {

    @BindingAdapter(value = ["currency:valueAttrChanged"])
    fun setListener(editText: MoneyTextInputEditText, listener: InverseBindingListener?) {
        Log.d("CurrencyBinder", "setListener(MoneyTextInputEditText, InverseBindingListener?)")
        if (listener != null) {
            editText.setAfterTextChangedListener {
                listener.onChange()
            }
        }
    }

    @BindingAdapter(value = ["currency:value"])
    fun setRealValue(view: MoneyTextInputEditText, liveData: MutableLiveData<Double>) {
        Log.d("CurrencyBinder", "setRealValue(MoneyTextInputEditText, MutableLiveData<Double>) -> ${liveData.value}")
        if (view.value != liveData.value)
            view.value = liveData.value ?: 0.0
    }

    @InverseBindingAdapter(attribute = "currency:value")
    fun getRealValue(view: MoneyTextInputEditText): Double = view.value

}