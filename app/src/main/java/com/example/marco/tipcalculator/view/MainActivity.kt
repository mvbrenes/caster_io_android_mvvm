package com.example.marco.tipcalculator.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.marco.tipcalculator.R
import com.example.marco.tipcalculator.databinding.ActivityMainBinding
import com.example.marco.tipcalculator.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        binding.toolbar.title = "Tip Calculator"
        setSupportActionBar(binding.toolbar)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
        super.onDestroy()
    }
}

const val TAG = "MainActivity"
