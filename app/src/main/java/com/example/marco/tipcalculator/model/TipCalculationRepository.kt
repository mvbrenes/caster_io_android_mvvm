package com.example.marco.tipcalculator.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TipCalculationRepository {

    private val saveTips = mutableMapOf<String, TipCalculation>()

    fun saveTipCalculation(tc: TipCalculation) {
        saveTips[tc.locationName] = tc
    }

    fun loadTipCalculationByName(locationName: String): TipCalculation? {
        return saveTips[locationName]
    }

    fun loadSavedTipCalculations(): LiveData<List<TipCalculation>> {
        val liveData = MutableLiveData<List<TipCalculation>>()
        liveData.value = saveTips.values.toList()
        return liveData
    }
}