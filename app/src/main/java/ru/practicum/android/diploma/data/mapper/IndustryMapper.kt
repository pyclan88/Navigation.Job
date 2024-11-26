package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.industry.IndustryResponse
import ru.practicum.android.diploma.domain.models.Industry

class IndustryMapper {

    fun map(dto: IndustryResponse): List<Industry> =
        dto.industries.flatMap { group ->
            group.industries.map { industry ->
                Industry(
                    id = industry.id,
                    name = industry.name
                )
            }
        }
}

