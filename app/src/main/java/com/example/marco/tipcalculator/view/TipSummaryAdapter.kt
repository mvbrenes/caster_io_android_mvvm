package com.example.marco.tipcalculator.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marco.tipcalculator.R
import com.example.marco.tipcalculator.databinding.SavedTipCalculationsListItemBinding
import com.example.marco.tipcalculator.viewmodel.TipCalculationSummaryItem

class TipSummaryAdapter(val onItemSelected: (item: TipCalculationSummaryItem) -> Unit) :
    RecyclerView.Adapter<TipSummaryAdapter.TipSummaryViewHolder>() {

    private val tipCalculationSummaries = mutableListOf<TipCalculationSummaryItem>()

    fun updateList(updates: List<TipCalculationSummaryItem>) {
        tipCalculationSummaries.clear()
        tipCalculationSummaries.addAll(updates)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipSummaryViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<SavedTipCalculationsListItemBinding>(
            inflater, R.layout.saved_tip_calculations_list_item, parent, false
        )

        return TipSummaryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tipCalculationSummaries.size
    }

    override fun onBindViewHolder(holder: TipSummaryViewHolder, position: Int) {
        holder.bind(tipCalculationSummaries[position])
    }

    inner class TipSummaryViewHolder(private val binding: SavedTipCalculationsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TipCalculationSummaryItem) {
            binding.item = item
            binding.root.setOnClickListener { onItemSelected(item) }
            binding.executePendingBindings()
        }
    }
}