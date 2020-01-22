package com.example.orangebank.view

import Transaction
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orangebank.R
import com.example.orangebank.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { presenter.retrieveTransactions() }

    }

    fun showTransactions(transactions: List<Transaction>) {
        val lastTransaction: Transaction = transactions.get(0)
        presenter.showLastTransaction(lastTransaction)
        var adapter = Adapter(transactions.subList(1, transactions.size))
        rvTransactions.setHasFixedSize(true)
        rvTransactions.layoutManager = LinearLayoutManager(this)
        rvTransactions.adapter = adapter
    }

    fun showLastTransaction(id: Int?, amount: Double, amountTextColor: Int, description: String?, date: String?) {
        transactionId.text = "$id"
        transactionAmount.text = "$amount€"
        transactionAmount.setTextColor(amountTextColor)
        transactionDescription.text = description
        transactionDate.text = date
    }
}
