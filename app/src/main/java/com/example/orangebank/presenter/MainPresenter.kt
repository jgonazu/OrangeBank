package com.example.orangebank.presenter

import Transaction
import android.graphics.Color
import com.example.orangebank.interactor.TransactionsInteractor
import com.example.orangebank.utils.Utils
import com.example.orangebank.view.MainActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.ParseException
import java.text.SimpleDateFormat


class MainPresenter(var view: MainActivity) : TransactionsInteractor.OnTrasactionsListener {

    override fun onTransactionsRetrieved(transactions: List<Transaction>) {

        view.showTransactions(transactions.filter {
            isValidDate(it.date)
        }.sortedByDescending { it.date }
            .distinctBy { it.id })
    }

    fun retrieveTransactions() {
        doAsync {
            val call = getRetrofit().create(TransactionsInteractor.APIService::class.java)
                .getTransactions().execute()
            val transactions = call.body() as List<Transaction>
            uiThread {
                onTransactionsRetrieved(transactions)
            }
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.myjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun isValidDate(date: String?): Boolean {
        if (date == null) return false
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        sdf.setLenient(false)
        try {
            sdf.parse(date)
        } catch (e: ParseException) {
            return false
        }

        return true
    }

    fun showLastTransaction(transaction: Transaction) {
        val amount = Utils.calculateAmount(transaction.amount, transaction.fee)
        val date = Utils.getFormattedDate(transaction.date)
        view.showLastTransaction(transaction.id, amount, Color.parseColor(Utils.getTextColor(amount)),
            transaction.description, date)
    }
}