package ru.practicum.android.diploma.data.network.clientIml

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.dto.VacanciesSearchResponse
import ru.practicum.android.diploma.data.dto.vacancy.details.VacancyDetailsResponse
import ru.practicum.android.diploma.data.network.HeadHunterApiService
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient

class DetailsNetworkClient(
    private val headHunterApiService: HeadHunterApiService
) : RetrofitNetworkClient(){
    suspend fun execute(vacancyId: String): Result<VacancyDetailsResponse> =
        withContext(Dispatchers.IO) {
            super.doRequest { headHunterApiService.getVacancyDetails(vacancyId) }
        }
}
