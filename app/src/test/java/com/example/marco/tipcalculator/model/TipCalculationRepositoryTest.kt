package com.example.marco.tipcalculator.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TipCalculationRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private lateinit var repository: TipCalculationRepository

    @Before
    fun setup() {
        repository = TipCalculationRepository()
    }

    @Test
    fun testSaveTip() {
        val tip = TipCalculation(
            locationName = "Pancake Paradise",
            checkAmount = 100.0, tipPct = 25,
            tipAmount = 25.0, grandTotal = 125.0
        )

        repository.saveTipCalculation(tip)

        assertEquals(tip, repository.loadTipCalculationByName(tip.locationName))
    }

    @Test
    fun testLoadSavedTipCalculation() {
        val tip1 = TipCalculation(
            locationName = "Pancake Paradise",
            checkAmount = 100.0, tipPct = 25,
            tipAmount = 25.0, grandTotal = 125.0
        )

        val tip2 = TipCalculation(
            locationName = "Veggie Sensation",
            checkAmount = 100.0, tipPct = 25,
            tipAmount = 25.0, grandTotal = 125.0
        )

        repository.apply {
            saveTipCalculation(tip1)
            saveTipCalculation(tip2)
            loadSavedTipCalculations().observeForever { tipCalculations ->
                assertEquals(2, tipCalculations?.size)
            }
        }
    }
}