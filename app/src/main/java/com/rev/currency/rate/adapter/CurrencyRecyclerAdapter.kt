package com.rev.currency.rate.adapter

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rev.currency.R
import com.rev.currency.rate.model.ExchangeRateItem

class CurrencyRecyclerAdapter(private var currencies: MutableList<ExchangeRateItem>,
                              private var listener: ICurrencyRecyclerAdapterListener): RecyclerView.Adapter<CurrencyRecyclerAdapter.CurrencyHolder>()  {

    override fun getItemCount(): Int {
        return currencies.size
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val rate = currencies[position]
        holder?.textCurrency?.text = rate.currency.name
        holder?.editTextPrice?.text = Editable.Factory.getInstance().newEditable(rate.displayPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        return CurrencyHolder(LayoutInflater.from(parent.context).inflate(R.layout.exchange_item_row, parent, false))
    }

    fun updateItems(currencies: MutableList<ExchangeRateItem>) {
        this.currencies = currencies
    }

    inner class CurrencyHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textCurrency: TextView = v.findViewById(R.id.text_currency)
        val editTextPrice: EditText = v.findViewById(R.id.edittext_price)

        init {
            v.setOnClickListener(moveToTop())

            editTextPrice?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    listener.onPriceInput(layoutPosition, p0.toString())
                }
            })

            editTextPrice.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    val block = moveToTop()
                    block(view)
                }
            }
        }

        private fun moveToTop(): (View) -> Unit = {
            layoutPosition.takeIf { it > 0 }?.also { currentPosition ->
                currencies.removeAt(currentPosition).also {
                    currencies.add(0, it)
                }
                notifyItemMoved(currentPosition, 0)
                listener.onItemMoved()
            }
        }
    }
}