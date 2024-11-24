package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.data.dto.VacanciesSearchResponse
import ru.practicum.android.diploma.data.dto.vacancy.details.VacancyDetailsDto

interface HeadHunterApiService {

    @GET("/vacancies")
    suspend fun searchVacancies(
        @QueryMap options: Map<String, String>
    ): VacanciesSearchResponse

    @GET("/vacancies/{vacancyId}")
    suspend fun getVacancyDetails(
        @Path("vacancyId") vacancyId: String
    ): VacancyDetailsDto

}
