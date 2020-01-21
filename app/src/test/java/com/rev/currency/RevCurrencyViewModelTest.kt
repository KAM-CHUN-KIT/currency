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
import org.mockito.Mock
import org.mockito.Mockito
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
    fun `test when fetchLatest resultDisposable not null`() {
        viewModel.getLatest(CurrencyType.EUR)
        Assert.assertNull(viewModel.currency.value)
    }

    @Test
    fun `test when updateExchangeRate`() {
        val mockCurrency = Currency(CurrencyType.EUR.name, "2018-11-11")

        viewModel.updateExchangeRate(mockCurrency)
        Assert.assertNull(viewModel.currencyList.value)
    }

    @Test
    fun `test when updateCurrencyListOrder`() {
        val list = mutableListOf<ExchangeRateItem>(ExchangeRateItem(CurrencyType.EUR, BigDecimal.ONE), ExchangeRateItem(CurrencyType.USD, BigDecimal("1.1634")))
        viewModel.updateCurrencyListOrder(list)
        Assert.assertEquals(list, viewModel.currencyList.value)
    }

//    @Test
//    fun `test when updateCurrencyBasePrice`() {
//        val expectedResult = Currency(CurrencyType.EUR.name, "2018-11-11")
//
//        viewModel.updateCurrencyBasePrice(CurrencyType.EUR, "100")
//
//        val captor = ArgumentCaptor.forClass(MutableList<ExchangeRateItem>::class.java)
//        captor.run {
//            verify(currencyListObserver, times(1)).onChanged(capture())
//            Assert.assertEquals(expectedResult, value)
//        }
//    }

    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}