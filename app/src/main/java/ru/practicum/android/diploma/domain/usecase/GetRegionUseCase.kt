package ru.practicum.android.diploma.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.domain.api.RegionRepository
import ru.practicum.android.diploma.util.Resource

class GetRegionUseCase(
    private val repository: RegionRepository
) {
    suspend fun execute() = withContext(Dispatchers.IO) {
        when (val result = repository.getCountry()) {
            is Resource.Success -> Pair(result.data, null)
            is Resource.Error -> Pair(null, result.message)
        }
    }
}
