package com.example.marco.tipcalculator.viewmodel

import android.app.Application
import com.example.marco.tipcalculator.R
import com.example.marco.tipcalculator.model.Calculator
import com.example.marco.tipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(
    app: Application, private val calculator: Calculator = Calculator()
) : ObservableViewModel(app) {

    private var lastTipCalculated = TipCalculation()

    var inputCheckAmount = ""
    var inputTipPercentage = ""
    val outputCheckAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.checkAmount)
    val outputTipAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.tipAmount)
    val outputTotalDollarAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.grandTotal)
    val locationName get() = lastTipCalculated.locationName

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        lastTipCalculated = tc
        notifyChange()
    }

    fun saveCurrentTip(name: String) {
        val tipToSave = lastTipCalculated.copy(locationName = name)//CurrentTipCalculation with LocationName added
        calculator.saveTipCalculation(tipToSave)
        updateOutputs(tipToSave)
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

    companion object {
        private val TAG = CalculatorViewModel::class.java.simpleName
    }
}
