package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.practicum.android.diploma.data.dto.VacancySearchResponse

interface HeadHunterApiService {

    @GET("/vacancies")
    suspend fun searchVacancy(
        @Query("text") text: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): VacancySearchResponse

}
