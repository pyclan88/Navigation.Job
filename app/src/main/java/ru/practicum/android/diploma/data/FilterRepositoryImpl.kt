package ru.practicum.android.diploma.data

import ru.practicum.android.diploma.data.datasourse.FilterStorage
import ru.practicum.android.diploma.domain.api.FilterRepository

class FilterRepositoryImpl(private val filterStorage: FilterStorage) : FilterRepository {
    override fun setFilters(items: List<String>) {
        filterStorage.filtersList = items
    }

    override fun getFilters(): List<String> {
        return filterStorage.filtersList
    }

}
