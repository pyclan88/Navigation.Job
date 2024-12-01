package ru.practicum.android.diploma.data

import ru.practicum.android.diploma.data.datasourse.FilterStorage
import ru.practicum.android.diploma.data.mapper.FilterMapper
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.models.Filter

class FilterRepositoryImpl(
    private val filterStorage: FilterStorage,
    private val filterMapper: FilterMapper
) : FilterRepository {

    override fun setFilters(value: Filter) {
        filterStorage.filters = filterMapper.map(value)
    }

    override fun getSearchFilters(): Filter {
        return filterMapper.map(filterStorage.searchFilters)
    }

    override fun setSearchFilters(value: Filter) {
        filterStorage.searchFilters = filterMapper.map(value)
    }

    override fun clearFilters() = setFilters(Filter.empty)

    override fun getFilters(): Filter {
        return filterMapper.map(filterStorage.filters)
    }
}
