package com.example.marco.tipcalculator.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.marco.tipcalculator.R
import com.example.marco.tipcalculator.databinding.ActivityMainBinding
import com.example.marco.tipcalculator.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = CalculatorViewModel(application)
        binding.toolbar.title = "Tip Calculator"
        setSupportActionBar(binding.toolbar)
    }
}
