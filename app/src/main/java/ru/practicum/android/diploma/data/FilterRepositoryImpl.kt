package ru.practicum.android.diploma.data

import ru.practicum.android.diploma.data.datasourse.FilterStorage
import ru.practicum.android.diploma.data.mapper.FilterMapper
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.models.Filter

class FilterRepositoryImpl(
    private val filterStorage: FilterStorage,
    private val filterMapper: FilterMapper
) : FilterRepository {

    override fun setTmpFilters(value: Filter) {
        filterStorage.tmpFilters = filterMapper.map(value)
    }

    override fun getSearchFilters(): Filter {
        return filterMapper.map(filterStorage.searchFilters)
    }

    override fun setSearchFilters(value: Filter) {
        filterStorage.searchFilters = filterMapper.map(value)
    }

    override fun clearFilters() = setTmpFilters(Filter.empty)

    override fun getTmpFilters(): Filter {
        return filterMapper.map(filterStorage.tmpFilters)
    }
}
