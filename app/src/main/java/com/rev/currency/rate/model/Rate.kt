package com.rev.currency.rate.model

import java.math.BigDecimal

data class Rate(val currency: CurrencyType,
                val rate: BigDecimal)