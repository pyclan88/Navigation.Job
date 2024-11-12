package ru.practicum.android.diploma.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.domain.api.VacancyRepository
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.util.Resource

class GetVacanciesUseCase(private val repository: VacancyRepository) {
     fun execute(expression: String, page: String): Flow<Pair<List<Vacancy>?, String?>> {
        return repository.searchVacancy(expression = expression, page = page).map {
            when (it) {
                is Resource.Success -> {
                    Pair(it.data, null)
                }

                is Resource.Error -> {
                    Pair(null, it.message)
                }
            }
        }
    }
}
