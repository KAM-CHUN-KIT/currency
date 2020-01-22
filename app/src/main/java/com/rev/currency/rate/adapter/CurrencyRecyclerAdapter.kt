package com.rev.currency.rate.adapter

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.rev.currency.R
import com.rev.currency.rate.model.CurrencyType
import com.rev.currency.rate.model.ExchangeRateItem

class CurrencyRecyclerAdapter(private var currencies: MutableList<ExchangeRateItem>,
                              private var listener: ICurrencyRecyclerAdapterListener): RecyclerView.Adapter<CurrencyRecyclerAdapter.CurrencyHolder>(), TextWatcher  {

    val drawableMap = hashMapOf(
        CurrencyType.EUR to R.drawable.ic_eur,
        CurrencyType.AUD to R.drawable.ic_aud,
        CurrencyType.BGN to R.drawable.ic_bgn,
        CurrencyType.BRL to R.drawable.ic_brl,
        CurrencyType.CAD to R.drawable.ic_cad,
        CurrencyType.CHF to R.drawable.ic_chf,
        CurrencyType.CNY to R.drawable.ic_cny,
        CurrencyType.CZK to R.drawable.ic_czk,
        CurrencyType.DKK to R.drawable.ic_dkk,
        CurrencyType.GBP to R.drawable.ic_gbp,
        CurrencyType.HKD to R.drawable.ic_hkd,
        CurrencyType.HRK to R.drawable.ic_hrk,
        CurrencyType.HUF to R.drawable.ic_huf,
        CurrencyType.IDR to R.drawable.ic_idr,
        CurrencyType.ILS to R.drawable.ic_ils,
        CurrencyType.INR to R.drawable.ic_inr,
        CurrencyType.ISK to R.drawable.ic_isk,
        CurrencyType.JPY to R.drawable.ic_jpy,
        CurrencyType.KRW to R.drawable.ic_krw,
        CurrencyType.MXN to R.drawable.ic_mxn,
        CurrencyType.MYR to R.drawable.ic_myr,
        CurrencyType.NOK to R.drawable.ic_nok,
        CurrencyType.NZD to R.drawable.ic_nzd,
        CurrencyType.PHP to R.drawable.ic_php,
        CurrencyType.PLN to R.drawable.ic_pln,
        CurrencyType.RON to R.drawable.ic_ron,
        CurrencyType.RUB to R.drawable.ic_rub,
        CurrencyType.SEK to R.drawable.ic_sek,
        CurrencyType.SGD to R.drawable.ic_sgd,
        CurrencyType.THB to R.drawable.ic_thb,
        CurrencyType.TRY to R.drawable.ic_try,
        CurrencyType.USD to R.drawable.ic_usd,
        CurrencyType.ZAR to R.drawable.ic_zar)


    override fun afterTextChanged(p0: Editable?) {
        listener.onPriceInput(currencies[0].currency, p0.toString()) // allow position to edit input only
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val rate = currencies[position]
        holder.editTextPrice.removeTextChangedListener(this)

        drawableMap[rate.currency]?.let {
            holder.imageCurrency.setImageResource(it)
        }
        holder.textCurrency.text = rate.currency.name
        holder.textCurrecnyAlias.text = rate.currency.name //show dummy test as no alias from API side
        holder.editTextPrice.text = Editable.Factory.getInstance().newEditable(rate.displayPrice)
        holder.editTextPrice.isEnabled = position == 0
        if (position == 0) {
            holder.editTextPrice.requestFocus()
            //only get first item input text
            holder.editTextPrice.addTextChangedListener(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        return CurrencyHolder(LayoutInflater.from(parent.context).inflate(R.layout.exchange_item_row, parent, false))
    }

    fun updateItems(currencies: MutableList<ExchangeRateItem>) {
        this.currencies = currencies
    }

    inner class CurrencyHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imageCurrency: ImageView = v.findViewById(R.id.image_currency)
        val textCurrency: TextView = v.findViewById(R.id.text_currency)
        val textCurrecnyAlias: TextView = v.findViewById(R.id.text_currency_alias)
        val editTextPrice: EditText = v.findViewById(R.id.edittext_price)

        init {
            v.setOnClickListener(moveToTop())

            editTextPrice.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    //Perform Code
                    return@OnKeyListener true
                }
                false
            })

        }

        private fun moveToTop(): (View) -> Unit = {
            layoutPosition.takeIf { it > 0 }?.also { currentPosition ->
                currencies.removeAt(currentPosition).also {
                    currencies.add(0, it)
                }
                notifyItemMoved(currentPosition, 0)
                listener.onItemMoved(currencies)
            }
        }
    }
}