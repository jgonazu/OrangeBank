package com.example.orangebank.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

internal object Utils {

    fun calculateAmount(amount: Double?, fee: Double?): Double {
        var amountNotNull: Double = amount?:0.0
        var feeNotNull:Double = fee?:0.0
        var totalAmount = amountNotNull.plus(amountNotNull.times(feeNotNull))
        return BigDecimal(totalAmount).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    }

    fun getTextColor(total: Double): String {
        if (total > 0)  {
            return "#0A8A06"
        } else {
            return "#8A1206"
        }
    }

    fun getFormattedDate(date: String?): String {
        val sdfIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val sdfOut = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        sdfIn.setLenient(false)
        var dateToReturn: Date?
        try {
            dateToReturn = sdfIn.parse(date?:"")
        } catch (e: ParseException) {
            return ""
        }
        return sdfOut.format(dateToReturn?:"")
    }
}
