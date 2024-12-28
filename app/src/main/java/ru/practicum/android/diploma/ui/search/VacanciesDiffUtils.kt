package ru.practicum.android.diploma.ui.search

import androidx.recyclerview.widget.DiffUtil
import ru.practicum.android.diploma.domain.models.Vacancy

class VacanciesDiffUtils(private val oldList: List<Vacancy>, private val newList: List<Vacancy>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id.toInt() == newList[newItemPosition].id.toInt()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id.toInt() == newList[newItemPosition].id.toInt()
    }
}
