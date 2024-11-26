package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.filter.FilterDto
import ru.practicum.android.diploma.domain.models.Filter

class FilterMapper {
    fun map(filterDto: FilterDto) = Filter(
        placeWork = filterDto.placeWork,
        industry = filterDto.industry,
        salary = filterDto.salary,
        withoutSalaryButton = filterDto.withoutSalaryButton
    )

    fun map(filter: Filter) = FilterDto(
        placeWork = filter.placeWork,
        industry = filter.industry,
        salary = filter.salary,
        withoutSalaryButton = filter.withoutSalaryButton
    )
}
