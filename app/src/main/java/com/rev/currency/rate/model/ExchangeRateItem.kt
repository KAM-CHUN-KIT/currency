package com.rev.currency.rate.model

import java.math.BigDecimal

data class ExchangeRateItem(val currency: CurrencyType,
                            var rate: BigDecimal,
                            var calculatedPrice: BigDecimal? = null) {

    val displayPrice: String
        get() {
            calculatedPrice?.let {
                if (it.compareTo(BigDecimal.ZERO) > 0) {
                    return it.toPlainString()
                } else {
                    return ""
                }
            }
            return rate.toPlainString()
        }
}