package com.rev.currency.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rev.currency.R
import com.rev.currency.rate.model.CurrencyType
import com.rev.currency.rate.viewmodel.RevCurrencyViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : RevBaseActivity() {
    private lateinit var viewModel: RevCurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        fetchData()
    }

    private fun initData(){
        viewModel = ViewModelProviders.of(this).get(RevCurrencyViewModel::class.java)
    }

    private fun fetchData(){
        viewModel.startRunnable()
        viewModel.currency.observe(this, Observer {
            text.text = it?.base
        })
    }
}
