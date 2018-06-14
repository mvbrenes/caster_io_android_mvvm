package com.example.marco.tipcalculator.viewmodel

import android.app.Application
import com.example.marco.tipcalculator.R
import com.example.marco.tipcalculator.model.Calculator
import com.example.marco.tipcalculator.model.TipCalculation
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CalculatorViewModelTest {

    lateinit var calculatorViewModel: CalculatorViewModel
    @Mock
    lateinit var mockCalculator: Calculator
    @Mock
    lateinit var application: Application

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        stubResource(0.0, "$0.00")
        calculatorViewModel = CalculatorViewModel(application, mockCalculator)
    }

    private fun stubResource(given: Double, returnStub: String) {
        `when`(application.getString(R.string.dollar_amount, given)).thenReturn(returnStub)
    }

    @Test
    fun testCalculatorTip() {
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"
        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)
        `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)
        stubResource(10.0, "$10.00")
        stubResource(1.5, "$1.50")
        stubResource(11.5, "$11.50")
        calculatorViewModel.calculateTip()

        assertEquals("$10.00", calculatorViewModel.outputCheckAmount)
        assertEquals("$1.50", calculatorViewModel.outputTipAmount)
        assertEquals("$11.50", calculatorViewModel.outputTotalDollarAmount)
    }

    @Test
    fun testCalculateBadTipPercent() {
        calculatorViewModel.inputTipPercentage = "10.00"
        calculatorViewModel.inputTipPercentage = ""
        calculatorViewModel.calculateTip()
        verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())
    }

    @Test
    fun testSaveCurrentTip() {
        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)

        fun setupTipCalculation() {
            calculatorViewModel.inputCheckAmount = "10.00"
            calculatorViewModel.inputTipPercentage = "15"
            `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)
        }

        setupTipCalculation()
        val name = "Green Eggs and Bacon"
        calculatorViewModel.calculateTip()
        calculatorViewModel.saveCurrentTip(name)

        verify(mockCalculator).saveTipCalculation(stub.copy(locationName = name))
        assertEquals(name, calculatorViewModel.locationName)
    }
}
