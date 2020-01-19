package com.rev.currency.activity

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rev.currency.R
import com.rev.currency.rate.adapter.CurrencyRecyclerAdapter
import com.rev.currency.rate.viewmodel.RevCurrencyViewModel
import kotlinx.android.synthetic.main.activity_main.*
import com.rev.currency.rate.adapter.ICurrencyRecyclerAdapterListener
import com.rev.currency.rate.model.CurrencyType
import com.rev.currency.rate.model.ExchangeRateItem

class MainActivity : RevBaseActivity(), ICurrencyRecyclerAdapterListener {
    private lateinit var viewModel: RevCurrencyViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CurrencyRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        currencyRecyclerView.layoutManager = linearLayoutManager

        setupViewModel()
        fetchData()
    }

    private fun setupViewModel(){
        viewModel = ViewModelProviders.of(this).get(RevCurrencyViewModel::class.java)
    }

    private fun fetchData(){
        viewModel.startRunnable()
        viewModel.currency.observe(this, Observer { currency ->
            viewModel.updateExchangeRate()
        })

        viewModel.currencyList.observe(this, Observer { currencyList ->
            if (!::adapter.isInitialized) {
                adapter = CurrencyRecyclerAdapter(currencyList, this)
                currencyRecyclerView.adapter = adapter
            } else {
                adapter.updateItems(currencyList)
                adapter.notifyItemRangeChanged(1, currencyList.size-1)
            }
        })
    }

    override fun onItemMoved(currencies: MutableList<ExchangeRateItem>) {
        super.onItemMoved(currencies)
        currencyRecyclerView.scrollToPosition(0) //scroll to top
        viewModel.reOrderCurrencyList(currencies)
    }

    override fun onPriceInput(base: CurrencyType, input: String) {
        super.onPriceInput(base, input)
        val r = Runnable {
            viewModel.editCurrencyBasePrice(base, input)
        }
        Handler().postDelayed(r, 1000)
    }
}
