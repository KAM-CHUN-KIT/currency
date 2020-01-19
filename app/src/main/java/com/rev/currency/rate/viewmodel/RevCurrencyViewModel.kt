package com.rev.currency.rate.viewmodel

import android.os.Handler
import androidx.lifecycle.*
import com.rev.currency.Util.Calculator
import com.rev.currency.rate.model.Currency
import com.rev.currency.rate.model.CurrencyType
import com.rev.currency.rate.model.ExchangeRateItem
import com.rev.currency.repository.RevCurrencyRepository
import io.reactivex.disposables.Disposable
import java.math.BigDecimal


class RevCurrencyViewModel: ViewModel() {

    val currency = MutableLiveData<Currency>()
    var currencyList = MutableLiveData<MutableList<ExchangeRateItem>>()
    private val currencylistRepository = RevCurrencyRepository()
    private var resultDisposable: Disposable? = null

    private var runnable: Runnable? = null
    private var handler: Handler? = null

    init {
        handler = Handler()
    }

    override fun onCleared() {
        super.onCleared()
        resultDisposable?.dispose() // to prevent memory leak
        handler?.removeCallbacks(runnable)
    }

    fun startRunnable() {
        runnable = object: Runnable {
            override fun run() {
                handler?.postDelayed(this, 1000)
                //should kill pending call in the queue
                getLatest(CurrencyType.EUR)
            }
        }
        runnable?.run()
    }

    fun updateExchangeRate() {
        currency.value?.let { base ->
            currencyList.value?.let { rates ->
                var list = mutableListOf<ExchangeRateItem>()
                for (rate in rates) {
                    val newRate = base.rates[rate.currency] ?: rate.rate
                    val r = ExchangeRateItem(rate.currency, newRate, rate.calculatedPrice)
                    list.add(r)
                }
                currencyList.value = list
            }
        }
    }

    fun reOrderCurrencyList(currencies: MutableList<ExchangeRateItem>) {
        currencyList.value = currencies
    }

    fun editCurrencyBasePrice(base: CurrencyType, input: String) {

        val currencyObject = currency.value?.takeIf { it != null } ?: Currency()
        val exchangeRateMap = currencyObject.rates
        val basePrice = if (input.isNotEmpty()) { BigDecimal(input) } else { BigDecimal.ZERO }
        currencyList.value?.let { rates ->
            var list = mutableListOf<ExchangeRateItem>()
            for (rate in rates) {
                val baseExchangeRate = exchangeRateMap[base] ?: BigDecimal.ONE //assume 1 if hashmap does not contain exchange rate e.g EUR
                val calPrice = Calculator.calculateExchangeRate(baseExchangeRate, rate.rate).multiply(basePrice) //multiply input value
                val r = ExchangeRateItem(rate.currency, rate.rate, calPrice)
                list.add(r)
            }
            currencyList.value = list
        }
    }


    //API call
    private fun getLatest(currencyKey: CurrencyType) {
        resultDisposable = currencylistRepository.getLatest(currencyKey)?.subscribe { result ->
            val (data, err) = result
            data?.let { data ->
                currency.value = data
                if (currencyList.value.isNullOrEmpty()) { //initialize currencyList
                    currencyList.value = data.asCurrencyList()
                }
            }

            err?.let {
                //error handling
            }
            resultDisposable?.dispose()
        }
    }

}