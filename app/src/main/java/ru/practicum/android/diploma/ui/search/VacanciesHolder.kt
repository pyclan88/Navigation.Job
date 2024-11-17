package ru.practicum.android.diploma.ui.search

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.VacancyItemBinding
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.ui.search.VacanciesAdapter.VacancyClickListener

class VacanciesHolder(
    private val binding: VacancyItemBinding,
    private val clickListener: VacancyClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(vacancy: Vacancy) = with(binding) {
        vacancy.imageUrl?.let { setLogo(it) }

        vacancyTitle.text = vacancy.name
        vacancyEmployer.text = vacancy.employerName
        vacancySalary.text = vacancy.salaryFrom

        itemView.setOnClickListener {
            clickListener.onVacancyClick(vacancy.id)
        }
    }

    private fun setLogo(url: String) = Glide.with(itemView)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.smile)
        .into(binding.vacancyLogo)
}
