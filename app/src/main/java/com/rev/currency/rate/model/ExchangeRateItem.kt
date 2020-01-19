package com.rev.currency.rate.model

import java.math.BigDecimal

data class ExchangeRateItem(val currency: CurrencyType,
                            var rate: BigDecimal,
                            var input: String? = null) {

    val displayPrice: String
        get() = input ?: rate.toPlainString()
}