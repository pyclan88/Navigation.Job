package ru.practicum.android.diploma.domain.usecase.filters

import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.models.Filter

class SetTmpFiltersUseCase(
    private val filterRepository: FilterRepository
) {

    fun execute(newItems: Filter) = filterRepository.setTmpFilters(newItems)

}
