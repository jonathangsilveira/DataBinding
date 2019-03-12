package br.edu.jonathangsilveira.kotlindatabinding.adapter

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.widget.TextView

object UiAdapter {

    @BindingAdapter("android:text")
    @JvmStatic
    fun setValue(view: TextView, newValue: String) {
        if (view.text != newValue) view.text = newValue
    }

    @InverseBindingAdapter(attribute = "android:text")
    @JvmStatic
    fun getValue(view: TextView): String = view.text.toString()

}