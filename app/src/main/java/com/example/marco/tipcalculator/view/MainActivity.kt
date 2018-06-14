package com.example.marco.tipcalculator.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.marco.tipcalculator.R
import com.example.marco.tipcalculator.databinding.ActivityMainBinding
import com.example.marco.tipcalculator.viewmodel.CalculatorViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), SaveDialogFragment.Callback {

    private lateinit var binding: ActivityMainBinding

    override fun onSaveTip(name: String) {
        binding.vm?.saveCurrentTip(name)
        Snackbar.make(binding.root, "Saved $name", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tip_calculator, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                showSaveDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSaveDialog() {
        val saveFragment = SaveDialogFragment()
        saveFragment.show(supportFragmentManager, "SaveDialog")
    }

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
