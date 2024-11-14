package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.util.Resource

interface VacancyRepository {

    suspend fun searchVacancy(expression: String, page: Int): Resource<List<Vacancy>>
}
