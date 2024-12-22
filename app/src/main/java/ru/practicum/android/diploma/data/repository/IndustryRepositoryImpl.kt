package ru.practicum.android.diploma.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.mapper.IndustryMapper
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.data.network.NetworkError
import ru.practicum.android.diploma.domain.api.IndustryRepository
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.util.Resource

class IndustryRepositoryImpl(
    private val networkClient: NetworkClient,
    private val industryMapper: IndustryMapper,
) : IndustryRepository {

    override suspend fun getIndustries(): Resource<List<Industry>> {
        return withContext(Dispatchers.IO) {
            // val response = networkClient.doRequest(IndustryRequest())
            Resource.Error(
                message = NetworkError
                    .NoData("requestName = response.javaClass.name")
                    .javaClass.name
            )
            /*when (response.resultCode) {
                FAILED_INTERNET_CONNECTION_CODE -> Resource.Error(
                    message = NetworkError
                        .NoInternet()
                        .javaClass.name
                )

                RetrofitNetworkClient.NOT_FOUND_CODE -> Resource.Error(
                    message = NetworkError
                        .NoData(requestName = response.javaClass.name)
                        .javaClass.name
                )

                SUCCESS_CODE -> {
                    val data = industryMapper.map(response as IndustryResponse)
                    Resource.Success(data)
                }

                else -> Resource.Error()
            }*/
        }
    }
}
