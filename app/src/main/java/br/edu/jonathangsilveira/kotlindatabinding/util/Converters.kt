package br.edu.jonathangsilveira.kotlindatabinding.util

import android.databinding.InverseMethod

object Converters {

    @JvmStatic
    fun format(value: Double?): String = value?.format() ?: 0.0.format()

    @InverseMethod("format")
    @JvmStatic
    fun toDouble(value: String?): Double = value?.toDouble() ?: 0.0

}