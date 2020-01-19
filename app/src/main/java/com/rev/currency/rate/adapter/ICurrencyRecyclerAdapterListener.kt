package com.rev.currency.rate.adapter

interface ICurrencyRecyclerAdapterListener {
    fun onItemMoved() = Unit
    fun onPriceInput(viewPosition: Int, input: String) = Unit
}