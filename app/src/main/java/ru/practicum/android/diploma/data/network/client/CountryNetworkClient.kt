package ru.practicum.android.diploma.data.network.client

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.dto.area.AreaDto
import ru.practicum.android.diploma.data.network.HeadHunterApiService
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient

class CountryNetworkClient(
    private val headHunterApiService: HeadHunterApiService
) : RetrofitNetworkClient() {
    suspend fun execute(): Result<List<AreaDto>> =
        withContext(Dispatchers.IO) {
            super.doRequest { headHunterApiService.getArea() }
        }
}
