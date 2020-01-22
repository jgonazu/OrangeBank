package com.example.orangebank.view

import Transaction
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orangebank.R
import com.example.orangebank.utils.Utils
import kotlinx.android.synthetic.main.item_transaction.view.*

class Adapter(val transactions: List<Transaction>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = transactions[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_transaction, parent, false))
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(transaction: Transaction) {
            itemView.transactionId.text = "${transaction.id}"
            val amount = Utils.calculateAmount(transaction.amount, transaction.fee)
            itemView.transactionAmount.text = "$amount€"
            itemView.transactionAmount.setTextColor(Color.parseColor(Utils.getTextColor(amount)))
            itemView.transactionDescription.text = transaction.description
            itemView.transactionDate.text = Utils.getFormattedDate(transaction.date)
        }
    }

}