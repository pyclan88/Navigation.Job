package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.util.Resource

interface VacancyRepository {

    suspend fun searchVacancy(expression: String, page: String): Resource<List<Vacancy>>
}
