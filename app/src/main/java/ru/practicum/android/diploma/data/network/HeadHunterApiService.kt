package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.data.dto.VacanciesSearchResponse
import ru.practicum.android.diploma.data.dto.area.AreaDto
import ru.practicum.android.diploma.data.dto.industry.IndustryItemDto
import ru.practicum.android.diploma.data.dto.vacancy.details.VacancyDetailsResponse

interface HeadHunterApiService {

    @GET("/vacancies")
    suspend fun searchVacancies(
        @QueryMap options: Map<String, String>
    ): VacanciesSearchResponse

    @GET("/vacancies/{vacancyId}")
    suspend fun getVacancyDetails(
        @Path("vacancyId") vacancyId: String
    ): VacancyDetailsResponse

    @GET("/industries")
    suspend fun getIndustries(): List<IndustryItemDto>

    @GET("/areas")
    suspend fun getArea(): List<AreaDto>
}
