package com.rev.currency.repository

import com.rev.currency.rate.model.CurrencyType

open class RevBaseRepository {

    fun LATEST(currency: CurrencyType): String {
        return "latest?base=$currency"
    }
}