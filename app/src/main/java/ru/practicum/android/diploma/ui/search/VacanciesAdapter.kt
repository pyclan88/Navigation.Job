package ru.practicum.android.diploma.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.VacancyItemBinding
import ru.practicum.android.diploma.domain.models.Vacancy

class VacanciesAdapter(private val listener: VacancyClickListener) : RecyclerView.Adapter<VacanciesHolder>() {

    private var vacancies: List<Vacancy> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return VacanciesHolder(VacancyItemBinding.inflate(layoutInspector, parent, false))
    }

    override fun onBindViewHolder(holder: VacanciesHolder, position: Int) {
        val vacancy = vacancies[position]
        holder.bind(vacancy)

        holder.itemView.setOnClickListener { listener.onVacancyClick(vacancy.id) }
    }

    override fun getItemCount(): Int = vacancies.size

    fun updateVacancies(newVacancies: List<Vacancy>) {
        val startPosition = vacancies.size
        vacancies = vacancies + newVacancies
        notifyItemRangeInserted(startPosition, newVacancies.size)
    }

    interface VacancyClickListener {
        fun onVacancyClick(vacancyId: String)
    }
}
