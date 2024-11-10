package ru.practicum.android.diploma.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.dto.Response
import ru.practicum.android.diploma.data.dto.VacancySearchRequest
import ru.practicum.android.diploma.util.getConnected

class RetrofitNetworkClient(private val headHunterApiService: HeadHunterApiService) : NetworkClient {
    override suspend fun doRequest(dto: Any): Response {
        if (!getConnected()) {
            return Response().apply { resultCode = -1 }
        }
        if (dto !is VacancySearchRequest) {
            return Response().apply { resultCode = 400 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = headHunterApiService.searchVacancyRequest(dto.expression, page = dto.page)
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }
}
