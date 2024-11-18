package ru.practicum.android.diploma.ui.search

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.VacancyItemBinding
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.ui.search.VacanciesAdapter.VacancyClickListener
import ru.practicum.android.diploma.util.SalaryFormatter

class VacanciesHolder(
    private val binding: VacancyItemBinding,
    private val clickListener: VacancyClickListener
) : RecyclerView.ViewHolder(binding.root) {
    private val salaryFormatter = SalaryFormatter(binding.root.context)
    fun bind(vacancy: Vacancy) = with(binding) {
        vacancy.imageUrl?.let { setLogo(it) }
        vacancyTitle.text = vacancy.name
        vacancyEmployer.text = vacancy.employerName
        vacancySalary.text = setSalary(vacancy)

        itemView.setOnClickListener {
            clickListener.onVacancyClick(vacancy.id)
        }
    }

    private fun setLogo(url: String) = Glide.with(itemView)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.smile)
        .into(binding.vacancyLogo)

    private fun setSalary(vacancy: Vacancy): String {
        return salaryFormatter.salaryFormat(
            vacancy.salaryFrom,
            vacancy.salaryTo,
            vacancy.currency,
        )
    }
}
