package ru.practicum.android.diploma.ui.adapters

import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.IndustryItemBinding
import ru.practicum.android.diploma.domain.models.Industry

class IndustryViewHolder(val binding: IndustryItemBinding) : RecyclerView.ViewHolder(binding.root) {

    val radioButton: RadioButton = binding.rbIndustryButton

    fun bind(industry: Industry) {
        binding.rbIndustryButton.text = industry.name
    }
}
