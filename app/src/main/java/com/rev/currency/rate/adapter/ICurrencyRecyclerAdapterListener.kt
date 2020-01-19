package com.rev.currency.rate.adapter

import com.rev.currency.rate.model.CurrencyType
import com.rev.currency.rate.model.ExchangeRateItem

interface ICurrencyRecyclerAdapterListener {
    fun onItemMoved(items: MutableList<ExchangeRateItem>) = Unit
    fun onPriceInput(base: CurrencyType, input: String) = Unit
}