package ru.practicum.android.diploma.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.practicum.android.diploma.databinding.CountryItemBinding
import ru.practicum.android.diploma.databinding.IndustryItemBinding
import ru.practicum.android.diploma.domain.models.Country
import ru.practicum.android.diploma.domain.models.Industry


class FilterAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var items: List<ItemFilter> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ItemFilter.TYPE_AREA -> CountryViewHolder(
                binding = CountryItemBinding.inflate(layoutInflater, parent, false)
            )
            ItemFilter.TYPE_INDUSTRY -> IndustryViewHolder(
                binding = IndustryItemBinding.inflate(layoutInflater, parent, false)
            )
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is CountryViewHolder -> holder.bind(item.data as Country)
            is IndustryViewHolder -> holder.bind(item.data as Industry)
            else -> throw IllegalArgumentException("Unknown ViewHolder for position $position")
        }
    }

    fun updateCountries(data: List<Country>) {
        this.items = convertToItemFilter(data, ItemFilter.TYPE_AREA)
        notifyDataSetChanged()
    }

    fun updateIndustries(data: List<Industry>) {
        this.items = convertToItemFilter(data, ItemFilter.TYPE_INDUSTRY)
        notifyDataSetChanged()
    }

    private fun convertToItemFilter(data: List<Any>, typeItemFilter: Int): List<ItemFilter> {
        return data.map {
            when (it) {
                is Country -> ItemFilter(data = it, type = typeItemFilter)
                is Industry -> ItemFilter(data = it, type = typeItemFilter)
                else -> throw IllegalArgumentException("Unknown type: ${it::class.java}")
            }
        }
    }
}


