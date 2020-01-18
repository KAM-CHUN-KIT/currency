package com.rev.currency.rate.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Rates(
    @SerializedName("AUD") val AUD: BigDecimal? = null,
    @SerializedName("BGN") val BGN: BigDecimal? = null,
    @SerializedName("BRL") val BRL: BigDecimal? = null,
    @SerializedName("CAD") val CAD: BigDecimal? = null,
    @SerializedName("CHF") val CHF: BigDecimal? = null,
    @SerializedName("CNY") val CNY: BigDecimal? = null,
    @SerializedName("CZK") val CZK: BigDecimal? = null,
    @SerializedName("DKK") val DKK: BigDecimal? = null,
    @SerializedName("EUR") val EUR: BigDecimal? = null,
    @SerializedName("GBP") val GBP: BigDecimal? = null,
    @SerializedName("HKD") val HKD: BigDecimal? = null,
    @SerializedName("HRK") val HRK: BigDecimal? = null,
    @SerializedName("HUF") val HUF: BigDecimal? = null,
    @SerializedName("IDR") val IDR: BigDecimal? = null,
    @SerializedName("ILS") val ILS: BigDecimal? = null,
    @SerializedName("INR") val INR: BigDecimal? = null,
    @SerializedName("ISK") val ISK: BigDecimal? = null,
    @SerializedName("JPY") val JPY: BigDecimal? = null,
    @SerializedName("KRW") val KRW: BigDecimal? = null,
    @SerializedName("MXN") val MXN: BigDecimal? = null,
    @SerializedName("MYR") val MYR: BigDecimal? = null,
    @SerializedName("NOK") val NOK: BigDecimal? = null,
    @SerializedName("NZD") val NZD: BigDecimal? = null,
    @SerializedName("PHP") val PHP: BigDecimal? = null,
    @SerializedName("RON") val RON: BigDecimal? = null,
    @SerializedName("RUB") val RUB: BigDecimal? = null,
    @SerializedName("SEK") val SEK: BigDecimal? = null,
    @SerializedName("SGD") val SGD: BigDecimal? = null,
    @SerializedName("PLN") val PLN: BigDecimal? = null,
    @SerializedName("THB") val THB: BigDecimal? = null,
    @SerializedName("TRY") val TRY: BigDecimal? = null,
    @SerializedName("USD") val USD: BigDecimal? = null,
    @SerializedName("ZAR") val ZAR: BigDecimal? = null
)