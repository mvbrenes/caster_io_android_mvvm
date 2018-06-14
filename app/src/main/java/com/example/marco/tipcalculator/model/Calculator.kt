package com.example.marco.tipcalculator.model

import androidx.lifecycle.LiveData
import java.math.RoundingMode

class Calculator(private val repository: TipCalculationRepository = TipCalculationRepository()) {
    fun calculateTip(checkAmount: Double, tipPct: Int): TipCalculation {
        val tipAmount = (checkAmount * (tipPct.toDouble() / 100.00)).toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP)
            .toDouble()

        val grandTotal = checkAmount + tipAmount
        return TipCalculation(
            checkAmount = checkAmount,
            tipPct = tipPct,
            tipAmount = tipAmount,
            grandTotal = grandTotal
        )
    }

    fun saveTipCalculation(tc: TipCalculation) {
        repository.saveTipCalculation(tc)
    }

    fun loadSaveTipCalculations(): LiveData<List<TipCalculation>> {
        return repository.loadSavedTipCalculations()
    }
}
