package br.edu.jonathangsilveira.kotlindatabinding.data

import android.app.Application
import br.edu.jonathangsilveira.kotlindatabinding.R
import br.edu.jonathangsilveira.kotlindatabinding.model.Transaction

object FakeRepository {

    val transactions: MutableList<Transaction> = mutableListOf()

    fun load(application: Application) {
        application.applicationContext.resources.openRawResource(R.raw.transactions).also {
            transactions.apply {
                clear()
            }
        }
    }

    fun filter(value: Double, method: Int): List<Transaction> = transactions.filter { transaction ->
        (value == 0.0 || transaction.value >= value) && transaction.method == method
    }

}