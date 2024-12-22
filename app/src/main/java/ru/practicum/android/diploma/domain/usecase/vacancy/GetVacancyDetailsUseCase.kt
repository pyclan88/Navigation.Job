package ru.practicum.android.diploma.domain.usecase.vacancy

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.domain.api.VacancyRepository

class GetVacancyDetailsUseCase(
    private val repository: VacancyRepository
) {

    suspend fun execute(id: String) = withContext(Dispatchers.IO) {
        repository.getVacancyDetails(id)
    }
}
