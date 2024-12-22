package ru.practicum.android.diploma.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.practicum.android.diploma.util.getConnected

abstract class RetrofitNetworkClient(
    // private val headHunterApiService: HeadHunterApiService
) : NetworkClient {

    override suspend fun <T> doRequest(request: suspend () -> T): Result<T> {
        if (!getConnected()) {
            return Result.failure(NetworkError.NoInternet())
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = request()
                Result.success(response)
                // обработать пустой ответ


//                when (requestDto) {
//                    is VacancySearchRequest -> {
//                        val res = headHunterApiService.searchVacancies(
//                            options = requestDto.options
//                        )
//                        Result.success(res)
//                    }
//
//                    is VacancyDetailsRequest -> Result.success(getVacancyDetails(requestDto))
//                    is IndustryRequest -> Result.success(getIndustries())
//                    is AreaRequest -> Result.success(getArea())
//                    else -> throw NetworkError.BadCode(
//                        requestDto.javaClass.name,
//                        BAD_REQUEST_CODE
//                    )/*Response().apply { resultCode = BAD_REQUEST_CODE }*/
//                }
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

    /*@Suppress("TooGenericExceptionCaught", "SwallowedException")
    override suspend fun doRequest(requestDto: Request): Response {
        if (!getConnected()) {
            return NetworkError.NoInternet()
        }
        return withContext(Dispatchers.IO) {
            try {
                when (requestDto) {
                    is VacancySearchRequest -> headHunterApiService.searchVacancies(
                        options = requestDto.options
                    ).apply { ResponseSuccess() }

                    is VacancyDetailsRequest -> getVacancyDetails(requestDto)
                    is IndustryRequest -> getIndustries()
                    is AreaRequest -> getArea()
                    else -> NetworkError.BadCode("${requestDto.javaClass}", BAD_REQUEST_CODE)
                }
            } catch (e: HttpException) {
                if (e.code() == NOT_FOUND_CODE) {
                    NetworkError.NoData("${requestDto.javaClass}")
                } else {
                    NetworkError.ServerError("${requestDto.javaClass}", "${e.code()}")
                }
            }
        }
    }*/

    /*private suspend fun searchVacancies(
        input: VacancySearchRequest
    ): VacanciesSearchResponse {
        return headHunterApiService.searchVacancies(
            options = input.options
        ).apply { ResponseSuccess() }
    }*/

//    private suspend fun getVacancyDetails(
//        input: VacancyDetailsRequest
//    ): VacancyDetailsResponse = headHunterApiService.getVacancyDetails(
//        vacancyId = input.id
//    ).apply { ResponseSuccess() }
//
//    private suspend fun getIndustries(): IndustryResponse {
//        val result = headHunterApiService.getIndustries()
//        return IndustryResponse(industries = result)
//            .apply { ResponseSuccess() }
//    }
//
//    private suspend fun getArea(): AreaResponse {
//        val result = headHunterApiService.getArea()
//        return AreaResponse(area = result)
//            .apply { ResponseSuccess() }
//    }

    companion object {
        const val FAILED_INTERNET_CONNECTION_CODE = -1
        const val SUCCESS_CODE = 200
        const val BAD_REQUEST_CODE = 400
        const val NOT_FOUND_CODE = 404
        const val SERVER_ERROR_CODE = 500
    }
}
