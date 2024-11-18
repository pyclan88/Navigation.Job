package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.VacancyDetails

interface FavoriteVacanciesRepository {

    suspend fun add(vacancy: VacancyDetails)

    suspend fun getById(id: String): VacancyDetails?

    suspend fun getAll(): List<VacancyDetails>

    suspend fun delete(vacancy: VacancyDetails)

}
