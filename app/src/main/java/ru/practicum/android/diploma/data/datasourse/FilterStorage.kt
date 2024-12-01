package ru.practicum.android.diploma.data.datasourse

import ru.practicum.android.diploma.data.dto.filter.FilterDto

interface FilterStorage {

    var tmpFilters: FilterDto

    var searchFilters: FilterDto

}
