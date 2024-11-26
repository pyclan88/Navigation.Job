package ru.practicum.android.diploma.domain.usecase.filters

import ru.practicum.android.diploma.domain.api.FilterRepository

class ClearFiltersUseCase(
    private val repository: FilterRepository
) {

    fun execute() = repository.clearFilters()

}
