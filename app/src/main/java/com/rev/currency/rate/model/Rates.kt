package com.rev.currency.rate.model

import com.google.gson.annotations.SerializedName

data class Rates(
    @SerializedName("AUD") val AUD: Double = 0.0,
    @SerializedName("BGN") val BGN: Double = 0.0,
    @SerializedName("BRL") val BRL: Double = 0.0,
    @SerializedName("CAD") val CAD: Double = 0.0,
    @SerializedName("CHF") val CHF: Double = 0.0,
    @SerializedName("CNY") val CNY: Double = 0.0,
    @SerializedName("CZK") val CZK: Double = 0.0,
    @SerializedName("DKK") val DKK: Double = 0.0,
    @SerializedName("EUR") val EUR: Double = 0.0,
    @SerializedName("GBP") val GBP: Double = 0.0,
    @SerializedName("HKD") val HKD: Double = 0.0,
    @SerializedName("HRK") val HRK: Double = 0.0,
    @SerializedName("HUF") val HUF: Double = 0.0,
    @SerializedName("IDR") val IDR: Double = 0.0,
    @SerializedName("ILS") val ILS: Double = 0.0,
    @SerializedName("INR") val INR: Double = 0.0,
    @SerializedName("ISK") val ISK: Double = 0.0,
    @SerializedName("JPY") val JPY: Double = 0.0,
    @SerializedName("KRW") val KRW: Double = 0.0,
    @SerializedName("MXN") val MXN: Double = 0.0,
    @SerializedName("MYR") val MYR: Double = 0.0,
    @SerializedName("NOK") val NOK: Double = 0.0,
    @SerializedName("NZD") val NZD: Double = 0.0,
    @SerializedName("PHP") val PHP: Double = 0.0,
    @SerializedName("RON") val RON: Double = 0.0,
    @SerializedName("RUB") val RUB: Double = 0.0,
    @SerializedName("SEK") val SEK: Double = 0.0,
    @SerializedName("SGD") val SGD: Double = 0.0,
    @SerializedName("PLN") val PLN: Double = 0.0,
    @SerializedName("THB") val THB: Double = 0.0,
    @SerializedName("TRY") val TRY: Double = 0.0,
    @SerializedName("USD") val USD: Double = 0.0,
    @SerializedName("ZAR") val ZAR: Double = 0.0
)