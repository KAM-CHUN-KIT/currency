package com.rev.currency.rate.viewmodel

import android.os.Handler
import androidx.lifecycle.*
import com.rev.currency.rate.model.Currency
import com.rev.currency.rate.model.CurrencyType
import com.rev.currency.repository.RevCurrencyRepository
import io.reactivex.disposables.Disposable


class RevCurrencyViewModel: ViewModel() {

    val currency = MutableLiveData<Currency>()

    private val currencylistRepository = RevCurrencyRepository()
    private var resultDisposable: Disposable? = null

    override fun onCleared() {
        super.onCleared()
        resultDisposable?.dispose() // to prevent memory leak
    }

    fun startRunnable() {
        val handler = Handler()
        val runnableCode = object: Runnable {
            override fun run() {
                handler.postDelayed(this, 1000)
                //should kill pending call in the queue
                getLatest(CurrencyType.EUR)
            }
        }
        runnableCode.run()
    }

    fun getLatest(currencyKey: CurrencyType) {
        resultDisposable = currencylistRepository.getLatest(currencyKey)?.subscribe { result ->
            val (data, err) = result
            if (data != null) {
                print(data)
                currency.value  = data
            } else if(err != null){

            }
            resultDisposable?.dispose()
        }
    }

}