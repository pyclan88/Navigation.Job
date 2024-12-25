package ru.practicum.android.diploma.data.network.client

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.dto.VacanciesSearchResponse
import ru.practicum.android.diploma.data.network.HeadHunterApiService
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient

class VacancyNetworkClient(
    private val headHunterApiService: HeadHunterApiService
) : RetrofitNetworkClient() {

    suspend fun execute(options: Map<String, Any>): Result<VacanciesSearchResponse> =
        withContext(Dispatchers.IO) {
            super.doRequest { headHunterApiService.searchVacancies(options) }
        }
}
