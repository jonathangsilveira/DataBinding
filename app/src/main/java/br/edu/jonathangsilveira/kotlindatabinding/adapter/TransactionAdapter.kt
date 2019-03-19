package br.edu.jonathangsilveira.kotlindatabinding.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.jonathangsilveira.kotlindatabinding.R
import br.edu.jonathangsilveira.kotlindatabinding.databinding.TransactionItemBinding
import br.edu.jonathangsilveira.kotlindatabinding.model.Transaction
import br.edu.jonathangsilveira.kotlindatabinding.util.format

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private val data: MutableList<Transaction> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): TransactionViewHolder {
        val binding = DataBindingUtil.inflate<TransactionItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.transaction_item,
            parent,
            false
        )
        return TransactionViewHolder(
            binding.root,
            binding
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        data[position].also {transaction ->
            with(holder.binding) {
                setValue(transaction.value.format())
                setDescription(transaction.description)
                executePendingBindings()
            }
        }
    }

    inner class TransactionViewHolder(
        itemView: View,
        val binding: TransactionItemBinding
    ) : RecyclerView.ViewHolder(itemView)

}