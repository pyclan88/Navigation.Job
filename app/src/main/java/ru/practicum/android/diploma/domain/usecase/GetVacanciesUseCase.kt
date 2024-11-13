package ru.practicum.android.diploma.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.domain.api.VacancyRepository
import ru.practicum.android.diploma.util.Resource

class GetVacanciesUseCase(private val repository: VacancyRepository) {
    suspend fun execute(expression: String, page: String) = withContext(Dispatchers.IO) {
        repository.searchVacancy(expression = expression, page = page).let {
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
