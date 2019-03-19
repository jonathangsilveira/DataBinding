package br.edu.jonathangsilveira.kotlindatabinding.data

import android.content.res.Resources
import br.edu.jonathangsilveira.kotlindatabinding.R
import br.edu.jonathangsilveira.kotlindatabinding.model.Transaction
import com.google.gson.Gson
import org.apache.commons.io.IOUtils

object FakeRepository {

    private val transactions: MutableList<Transaction> = mutableListOf()

    fun load(resources: Resources) {
        resources.openRawResource(R.raw.transactions).also {
            transactions.apply {
                clear()
                IOUtils.toString(it, Charsets.UTF_8).also { json ->
                    Gson().fromJson(json, Array<Transaction>::class.java)?.also { transactions ->
                        addAll(transactions.asList())
                    }
                }
            }
        }
    }

    fun filter(value: Double, method: Int): List<Transaction> =
        transactions.filter { transaction ->
            (value == 0.0 || transaction.value >= value) && transaction.method == method
        }

}