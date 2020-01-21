package com.rev.currency

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rev.currency.rate.model.Currency
import com.rev.currency.rate.model.CurrencyType
import com.rev.currency.rate.model.ExchangeRateItem
import com.rev.currency.rate.viewmodel.RevCurrencyViewModel
import com.rev.currency.repository.RevCurrencyRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import java.math.BigDecimal

class RevCurrencyViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule() //allow to run LiveData synchronously

    private lateinit var viewModel: RevCurrencyViewModel

    @Mock
    private val repository: RevCurrencyRepository = mock()
    @Mock
    private val currencyListObserver: Observer<MutableList<ExchangeRateItem>> = mock()
    @Mock
    private val currencyBaseObserver: Observer<Currency> = mock()

    @Before
    fun before() {
        viewModel = RevCurrencyViewModel(repository)
        viewModel.currency.observeForever(currencyBaseObserver)
        viewModel.currencyList.observeForever(currencyListObserver)
    }

    @Test
    fun `test when updateExchangeRate`() {
        val mockCurrency = Currency(CurrencyType.EUR.name, "2018-11-11", hashMapOf(CurrencyType.USD to BigDecimal("1.2345"), CurrencyType.HKD to BigDecimal("9.8765")))

        val mockInput = mutableListOf(
            ExchangeRateItem(CurrencyType.USD, BigDecimal("1.1587")),
            ExchangeRateItem(CurrencyType.HKD, BigDecimal("9.0953"))
        )

        val expectedOutput = mutableListOf(
            ExchangeRateItem(CurrencyType.USD, BigDecimal("1.2345")),
            ExchangeRateItem(CurrencyType.HKD, BigDecimal("9.8765"))
        )
        viewModel.currencyList.value = mockInput
        viewModel.updateExchangeRate(mockCurrency)

        val captor = ArgumentCaptor.forClass(MutableList::class.java)
        captor.run {
            verify(currencyListObserver, times(2)).onChanged(capture() as? MutableList<ExchangeRateItem>)
            Assert.assertEquals(expectedOutput, value)
        }
    }

    @Test
    fun `test when updateCurrencyListOrder`() {
        val list = mutableListOf<ExchangeRateItem>(ExchangeRateItem(CurrencyType.EUR, BigDecimal.ONE), ExchangeRateItem(CurrencyType.USD, BigDecimal("1.1634")))
        viewModel.updateCurrencyListOrder(list)
        Assert.assertEquals(list, viewModel.currencyList.value)
    }

    @Test
    fun `test when updateCurrencyBasePrice by 100 EUR`() {
        val baseCurrencyPrice = "100"

        val mockInput = mutableListOf(
            ExchangeRateItem(CurrencyType.USD, BigDecimal("1.1587")),
            ExchangeRateItem(CurrencyType.HKD, BigDecimal("9.0953"))
        )

        val expectedOutput = mutableListOf(
            ExchangeRateItem(CurrencyType.USD, BigDecimal("1.1587"), baseExchangeRate = BigDecimal.ONE, basePrice = BigDecimal(baseCurrencyPrice)),
            ExchangeRateItem(CurrencyType.HKD, BigDecimal("9.0953"), baseExchangeRate = BigDecimal.ONE, basePrice = BigDecimal(baseCurrencyPrice))
        )
        viewModel.currencyList.value = mockInput
        viewModel.updateCurrencyBasePrice(CurrencyType.EUR, baseCurrencyPrice)

        val captor = ArgumentCaptor.forClass(MutableList::class.java)
        captor.run {
            verify(currencyListObserver, times(2)).onChanged(capture() as? MutableList<ExchangeRateItem>)
            Assert.assertEquals(expectedOutput, value)
            Assert.assertEquals(expectedOutput[0].displayPrice, "115.87")
            Assert.assertEquals(expectedOutput[1].displayPrice, "909.53")
        }
    }

    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}