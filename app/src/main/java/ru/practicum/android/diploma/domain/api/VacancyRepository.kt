package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.util.Resource

interface VacancyRepository {

    fun searchVacancy(expression: String, page: String): Flow<Resource<List<Any>>>
}
