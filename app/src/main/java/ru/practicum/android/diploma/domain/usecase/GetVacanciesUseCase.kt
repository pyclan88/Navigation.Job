package ru.practicum.android.diploma.domain.usecase

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.domain.api.VacancyRepository
import ru.practicum.android.diploma.util.Resource

class GetVacanciesUseCase(
    private val repository: VacancyRepository
) {

    suspend fun execute(expression: String, page: Int) = withContext(Dispatchers.IO) {
        when (val result = repository.searchVacancies(expression = expression, page = page)) {
            is Resource.Success -> {
                System.out.println("UseCase${result.data}")
                Pair(result.data, null)
            }

            is Resource.Error -> Pair(null, result.message)
        }
    }
}
