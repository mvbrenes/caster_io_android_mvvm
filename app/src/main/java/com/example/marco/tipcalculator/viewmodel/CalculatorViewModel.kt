package com.example.marco.tipcalculator.viewmodel

import android.app.Application
import com.example.marco.tipcalculator.R
import com.example.marco.tipcalculator.model.Calculator
import com.example.marco.tipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(
    app: Application, private val calculator: Calculator = Calculator()
) : ObservableViewModel(app) {

    var inputCheckAmount = ""
    var inputTipPercentage = ""
    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotalDollarAmount = ""

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        outputCheckAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.checkAmount)
        outputTipAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.tipAmount)
        outputTotalDollarAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.grandTotal)
        notifyChange()
    }

    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()
        if (checkAmount != null && tipPct != null) {
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
        }
    }

    private fun clearInputs() {
        inputCheckAmount = "$0.00"
        inputTipPercentage = "0"
        notifyChange()
    }
}

private const val TAG = "CalculatorViewModel"
