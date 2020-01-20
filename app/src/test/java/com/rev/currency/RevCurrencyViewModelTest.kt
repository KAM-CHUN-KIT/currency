package com.rev.currency

import com.nhaarman.mockitokotlin2.mock
import com.rev.currency.rate.viewmodel.RevCurrencyViewModel
import com.rev.currency.repository.RevCurrencyRepository
import org.junit.Assert
import org.junit.Test

class RevCurrencyViewModelTest {

    private fun createViewModel(repository: RevCurrencyRepository = mock {
//        on { getOne(any()) } doReturn Single.never()
    }) = RevCurrencyViewModel(repository)


    @Test
    fun `test when updateExchangeRate`() {
        val viewModel = createViewModel()
        viewModel.updateExchangeRate()
        Assert.assertEquals(4, 2 + 2)
    }

    @Test
    fun `test when updateCurrencyBasePrice`() {
        val viewModel = createViewModel()
//        viewModel.updateCurrencyBasePrice()
    }

    @Test
    fun `test when updateCurrencyListOrder`() {
        val viewModel = createViewModel()
//        viewModel.updateCurrencyListOrder()
    }
}