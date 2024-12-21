package ru.practicum.android.diploma.data.network

import ru.practicum.android.diploma.data.dto.Response

class VacancyNetworkClient(headHunterApiService: HeadHunterApiService) : RetrofitNetworkClient(headHunterApiService) {
    override suspend fun doRequest(requestDto: Request): Result<Response> {
        return super.doRequest(requestDto)
    }
}
