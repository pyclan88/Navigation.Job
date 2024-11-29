package ru.practicum.android.diploma.ui.adapters.industry

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.IndustryItemBinding
import ru.practicum.android.diploma.domain.models.Industry

class IndustryAdapter(
    private val clickListener: IndustryClickListener
) : RecyclerView.Adapter<IndustryViewHolder>() {

    private var lastCheckedIndustry: Industry? = null
    private var lastPosition: Int = -1
    private var industries: List<Industry> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndustryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = IndustryItemBinding.inflate(layoutInflater, parent, false)
        return IndustryViewHolder(binding)
    }

    override fun getItemCount(): Int = industries.size

    override fun onBindViewHolder(holder: IndustryViewHolder, position: Int) {
        with(holder) {
            val industry = industries[position]
            bind(industry)

            itemView.setOnClickListener {
                clickListener.onIndustryClick(industry)

                lastCheckedIndustry?.let { it.isSelected = false }
                notifyItemChanged(lastPosition)
                lastPosition = adapterPosition

                industries[adapterPosition].isSelected = true
                lastCheckedIndustry = industries[adapterPosition]
                notifyItemChanged(adapterPosition)
            }
        }
    }

    fun updateIndustries(industries: List<Industry>) {
        lastCheckedIndustry?.let {
            val match = industries.find { industry -> industry.id == it.id }
            match?.isSelected = it.isSelected
        }

        this.industries = industries
        notifyDataSetChanged()
    }

    fun interface IndustryClickListener {
        fun onIndustryClick(industry: Industry)
    }
}
