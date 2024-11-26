package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.Filter

interface FilterRepository {
    fun setFilters(value: Filter)
    fun getFilters(): Filter
}
