package com.rev.currency.rate.model

import com.github.kittinunf.fuel.core.Deserializable
import com.github.kittinunf.fuel.core.Response
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.nio.charset.Charset

data class Currency(
    @SerializedName("base") val base: String = "",
    @SerializedName("date") val date: String = "",
    @SerializedName("rates") val rates: Rates = Rates()
) {
    class Deserializer : Deserializable<Currency> {
        override fun deserialize(response: Response): Currency {
            return Gson().fromJson(
                response.data.toString(Charset.defaultCharset()),
                Currency::class.java
            )
        }
    }
}