package br.edu.jonathangsilveira.kotlindatabinding.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.jonathangsilveira.kotlindatabinding.R
import br.edu.jonathangsilveira.kotlindatabinding.databinding.TransactionItemBinding

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

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

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {

    }

    inner class TransactionViewHolder(
        itemView: View,
        val binding: TransactionItemBinding
    ) : RecyclerView.ViewHolder(itemView) {



    }

}