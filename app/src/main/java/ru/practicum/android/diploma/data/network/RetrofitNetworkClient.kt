package ru.practicum.android.diploma.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.dto.Response
import ru.practicum.android.diploma.data.dto.VacancySearchRequest
import ru.practicum.android.diploma.util.getConnected

class RetrofitNetworkClient(
    private val headHunterApiService: HeadHunterApiService
) : NetworkClient {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun doRequest(dto: Any): Response {
        return if (!getConnected()) {
            Response().apply { resultCode = FAILED_INTERNET_CONNECTION_CODE }
        } else if (dto !is VacancySearchRequest) {
            Response().apply { resultCode = BAD_REQUEST_CODE }
        } else {
            withContext(Dispatchers.IO) {
                try {
                    headHunterApiService.searchVacancyRequest(
                        text = dto.expression,
                        page = dto.page
                    ).apply { resultCode = SUCCESS_CODE }
                } catch (e: Throwable) {
                    Log.e("stackTrace", "${e.printStackTrace()}")
                    Log.e(RetrofitNetworkClient::class.simpleName, e.message, e)
                    Response().apply { resultCode = SERVER_ERROR_CODE }
                }
            }
        }
    }

    companion object {
        const val FAILED_INTERNET_CONNECTION_CODE = -1
        const val SUCCESS_CODE = 200
        const val BAD_REQUEST_CODE = 400
        const val SERVER_ERROR_CODE = 500
    }
}
