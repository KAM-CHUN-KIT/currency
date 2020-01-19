package com.rev.currency.Util

import java.math.BigDecimal
import java.math.MathContext

object Calculator {
    fun calculateExchangeRate(first: BigDecimal, second: BigDecimal): BigDecimal {
        // = 1/first * second  //Base EUR exchange rate equal to 1
        val base = BigDecimal.ONE.divide(first, MathContext.DECIMAL128)
        val value = base.multiply(second)

        return value
    }
}