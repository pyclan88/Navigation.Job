package ru.practicum.android.diploma.domain.usecase.filters

import ru.practicum.android.diploma.domain.api.FilterRepository

class SetFiltersUseCase(private val filterRepository: FilterRepository) {
    fun execute(newItems: List<String>) {
        filterRepository.setFilters(newItems)
    }
}
