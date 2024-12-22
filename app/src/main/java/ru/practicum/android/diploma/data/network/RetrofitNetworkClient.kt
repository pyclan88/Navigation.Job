package ru.practicum.android.diploma.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.practicum.android.diploma.util.getConnected

abstract class RetrofitNetworkClient : NetworkClient {

    override suspend fun <T> doRequest(request: suspend () -> T): Result<T> {
        if (!getConnected()) {
            return Result.failure(NetworkError.NoInternet())
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = request()
                Result.success(response)
                // обработать пустой ответ

            } catch (e: HttpException) {
                Result.failure(
                    if (e.code() == NOT_FOUND_CODE) {
                        NetworkError.NoData("requestDto.javaClass.name")
                    } else {
                        NetworkError.ServerError(
                            "requestDto.javaClass.name",
                            e.toString()
                        )
                    }
                )
            }
        }
    }

    companion object {
        const val FAILED_INTERNET_CONNECTION_CODE = -1
        const val SUCCESS_CODE = 200
        const val BAD_REQUEST_CODE = 400
        const val NOT_FOUND_CODE = 404
        const val SERVER_ERROR_CODE = 500
    }
}
