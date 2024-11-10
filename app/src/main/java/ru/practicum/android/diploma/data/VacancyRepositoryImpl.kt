package ru.practicum.android.diploma.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.domain.api.VacancyRepository
import ru.practicum.android.diploma.util.Resource

class VacancyRepositoryImpl(private val networkClient: NetworkClient) : VacancyRepository {
    override fun searchVacancy(expression: String, page: String): Flow<Resource<List<Any>>> = flow {
        val response = networkClient.doRequest(searchVacancy(expression, page))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("-1"))
            }

            200 -> {

            }

            else -> {
                emit(Resource.Error("Server Error"))
            }
        }
    }
}
