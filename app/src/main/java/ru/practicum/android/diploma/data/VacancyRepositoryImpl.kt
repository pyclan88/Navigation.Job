package ru.practicum.android.diploma.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.dto.VacancySearchResponse
import ru.practicum.android.diploma.data.mapper.VacancyResponseMapper
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient
import ru.practicum.android.diploma.domain.api.VacancyRepository
import ru.practicum.android.diploma.util.Resource

class VacancyRepositoryImpl(
    private val networkClient: NetworkClient, private val mapper: VacancyResponseMapper
) : VacancyRepository {

    override fun searchVacancy(expression: String, page: String): Flow<Resource<List<Any>>> = flow {
        val response = networkClient.doRequest(searchVacancy(expression, page))
        when (response.resultCode) {
            RetrofitNetworkClient.FAILED_INTERNET_CONNECTION_CODE -> {
                emit(Resource.Error("-1"))
            }

            RetrofitNetworkClient.SUCCESS_CODE -> {
                Log.e(VacancyRepository::class.simpleName, response.toString())
                val data = mapper.map(response as VacancySearchResponse)
                emit(Resource.Success(data))
            }

            else -> {
                emit(Resource.Error("Server Error"))
            }
        }
    }
}
