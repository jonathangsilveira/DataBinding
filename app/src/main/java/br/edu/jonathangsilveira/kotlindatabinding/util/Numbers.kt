package br.edu.jonathangsilveira.kotlindatabinding.util

import android.widget.TextView
import java.text.DecimalFormat
import java.util.*

fun Number.format(locale: Locale = Locale.getDefault(), minIntDigits: Int = 1, minFracDigits: Int = 2): String {
    return DecimalFormat.getNumberInstance(locale).apply {
        isGroupingUsed = true
        minimumIntegerDigits = minIntDigits
        minimumFractionDigits = minFracDigits
    }.format(this)
}

fun TextView.toDouble(locale: Locale = Locale.getDefault()): Double {
    return DecimalFormat.getNumberInstance(locale).apply {
        isGroupingUsed = true
        minimumIntegerDigits = 1
        minimumFractionDigits = 2
    }.parse(this.text.toString()).toDouble()
}