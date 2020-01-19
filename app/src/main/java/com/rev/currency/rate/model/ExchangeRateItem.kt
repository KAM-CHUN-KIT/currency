package com.rev.currency.rate.model

import com.rev.currency.Util.Calculator
import java.math.BigDecimal

data class ExchangeRateItem(val currency: CurrencyType,
                            var rate: BigDecimal,
                            val baseExchangeRate: BigDecimal? = null,
                            val basePrice: BigDecimal? = null) {

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

    val calculatedPrice: BigDecimal?
        get() {
            baseExchangeRate?.let { baseExchangeRate ->
                basePrice?.let { basePrice ->
                    return Calculator.calculateExchangeRate(baseExchangeRate, rate).multiply(basePrice) //multiply input value
                }
            }
            return null
        }
}