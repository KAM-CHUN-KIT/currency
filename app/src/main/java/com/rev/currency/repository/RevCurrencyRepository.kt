package com.rev.currency.repository

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.rx.rxObject
import com.github.kittinunf.result.Result
import com.rev.currency.rate.model.Currency
import com.rev.currency.rate.model.CurrencyType
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RevCurrencyRepository : RevBaseRepository() {
    fun getLatest(currency: CurrencyType): Single<Result<Currency, FuelError>>? =
                Fuel.get(LATEST(currency)).rxObject(Currency.Deserializer()).subscribeOn(Schedulers.newThread()).observeOn(
                    AndroidSchedulers.mainThread())
}