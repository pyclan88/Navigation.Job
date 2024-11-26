package ru.practicum.android.diploma.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.IndustryItemBinding
import ru.practicum.android.diploma.domain.models.Industry

class IndustryViewHolder(
    private val binding: IndustryItemBinding,
    private val clickListener: FilterAdapter.FilterClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(industry: Industry) {
        binding.rbIndustryButton.text = industry.name
        binding.rbIndustryButton.isChecked =false
        binding.rbIndustryButton.setOnClickListener {
            clickListener.onFilterClick(filterId = industry.id)
        }
    }
}

