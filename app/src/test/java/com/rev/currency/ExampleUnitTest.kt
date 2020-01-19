package com.rev.currency

import com.rev.currency.Util.Calculator
import org.junit.Test

import org.junit.Assert.*
import java.math.BigDecimal

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun hkdToUSD_isCorrect() {
        //base is EUR
        val hkd = BigDecimal("9.1173")
        val usd = BigDecimal("1.1615")

        Calculator.calculateExchangeRate(hkd, usd)
    }
}
