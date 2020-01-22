package com.example.orangebank.interactor

import Transaction
import retrofit2.Call
import retrofit2.http.GET


class TransactionsInteractor {

    interface OnTrasactionsListener {
        fun onTransactionsRetrieved(transactions: List<Transaction>)
    }

    interface APIService {
        @GET("bins/1a30k8")
        fun getTransactions(): Call<List<Transaction>>
    }

}