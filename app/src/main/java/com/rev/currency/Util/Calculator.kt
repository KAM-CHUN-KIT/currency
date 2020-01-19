package com.rev.currency.Util

import java.math.BigDecimal

object Calculator {
    fun calculateExchangeRate(first: BigDecimal, second: BigDecimal): BigDecimal {
        // = 1/first * second
        val base = BigDecimal("1").div(first)
        val value = base.multiply(second)

        return value
    }
}