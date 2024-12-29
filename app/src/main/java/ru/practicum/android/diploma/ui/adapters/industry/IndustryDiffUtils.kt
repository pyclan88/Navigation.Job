package ru.practicum.android.diploma.ui.adapters.industry

import androidx.recyclerview.widget.DiffUtil

class IndustryDiffUtils(
    private val oldList: List<IndustryItem>,
    private val newList: List<IndustryItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].industry.id == newList[newItemPosition].industry.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isChecked == newList[newItemPosition].isChecked
    }
}
