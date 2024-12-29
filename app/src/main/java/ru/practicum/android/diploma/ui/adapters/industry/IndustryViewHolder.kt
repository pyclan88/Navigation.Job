package ru.practicum.android.diploma.ui.adapters.industry

import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.IndustryItemBinding

class IndustryViewHolder(
    private val binding: IndustryItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(industry: IndustryItem, isChecked: Boolean) = with(binding) {
        rbIndustryButton.text = industry.industry.name
        rbIndustryButton.isChecked = isChecked
    }
}
